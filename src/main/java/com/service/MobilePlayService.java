package com.service;

import com.constant.AceEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Classname: MobilePlayService
 * @Date: 2022/11/15 下午 11:58
 * @Author: kalam_au
 * @Description:
 */

@Service
public class MobilePlayService {
    private static final Logger log = LogManager.getLogger(MobilePlayService.class.getName());

    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_CONTENT_LENGTH = "Content-Length";
    private static final String HEADER_ACCEPT_RANGES = "Accept-Ranges";
    private static final String HEADER_CONTENT_RANGE = "Content-Range";
    private static final String CONTENT_TYPE_MP4 = "video/mp4";

    private final String videoPath;

    public MobilePlayService() {
        this.videoPath = AceEnvironment.getVideoPath();
    }


    public ResponseEntity<StreamingResponseBody> getResponseStream(String mediaName, String rangeHeader) {
        try {
            StreamingResponseBody responseStream;
            final HttpHeaders responseHeaders = new HttpHeaders();

            final Path filePath = Paths.get(mediaName);
            final long fileSize = Files.size(filePath);

            byte[] buffer = new byte[1024];
            if (rangeHeader == null) {
                responseHeaders.add(HEADER_CONTENT_TYPE, CONTENT_TYPE_MP4);
                responseHeaders.add(HEADER_CONTENT_LENGTH, Long.toString(fileSize));
                responseStream = os -> {
                    try (RandomAccessFile file = new RandomAccessFile(filePath.toFile(), "r")) {
                        long pos = 0;
                        file.seek(pos);

                        while (pos < fileSize) {
                            file.read(buffer);
                            os.write(buffer);
                            pos += buffer.length;
                        }

                        os.flush();
                        os.close();
                    } catch (Exception ignored) {
                        ignored.printStackTrace();
                    }
                };
                return new ResponseEntity<>(responseStream, responseHeaders, HttpStatus.OK);
            } else {
                // Handle partial content requests
                String[] ranges = rangeHeader.split("-");
                long rangeStart = Long.parseLong(ranges[0].substring(6));
                long rangeEnd;

                if (ranges.length > 1) {
                    rangeEnd = Long.parseLong(ranges[1]);
                } else {
                    // If range is not found then just request the whole file
                    rangeEnd = fileSize - 1;
                }

                // Check to make sure that content range is not outside the length of the file
                if (fileSize < rangeEnd) {
                    rangeEnd = fileSize - 1;
                }

                log.info("Received request for byte range: {} - {}", rangeStart, rangeEnd);

                String contentLength = String.valueOf((rangeEnd - rangeStart) + 1);
                responseHeaders.add(HEADER_CONTENT_TYPE, CONTENT_TYPE_MP4);
                responseHeaders.add(HEADER_CONTENT_LENGTH, contentLength);
                responseHeaders.add(HEADER_ACCEPT_RANGES, "bytes");
                responseHeaders.add(HEADER_CONTENT_RANGE, "bytes" + " " + rangeStart + "-" + rangeEnd + "/" + fileSize);

                final long _rangeEnd = rangeEnd;
                responseStream = os -> {
                    try (RandomAccessFile file = new RandomAccessFile(filePath.toFile(), "r")) {
                        long pos = rangeStart;
                        file.seek(pos);

                        while (pos < _rangeEnd) {
                            file.read(buffer);
                            os.write(buffer);
                            pos += buffer.length;
                        }

                        os.flush();
                    } catch (Exception ignored) {
                        ignored.printStackTrace();
                    }
                };
                return new ResponseEntity<>(responseStream, responseHeaders, HttpStatus.PARTIAL_CONTENT);
            }
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

