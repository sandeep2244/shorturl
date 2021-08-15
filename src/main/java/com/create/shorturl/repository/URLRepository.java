package com.create.shorturl.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.Scanner;


@Repository
public class URLRepository {

    private final static String FILENAME = "C:\\File\\shortURL.txt";   //Please update local file path here
    private static final Logger LOGGER = LoggerFactory.getLogger(URLRepository.class);

    public URLRepository() {
    }

 
    public void saveUrl(Long id,String uniqueId,String shortenedURL,String longUrl) {
        LOGGER.info("Saving: {} at EOF file", longUrl);
        try(FileWriter fw = new FileWriter(FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
            {
            out.println(String.valueOf(id)+'|'+uniqueId+'|'+shortenedURL+'|'+longUrl);   //Insert record on Memory(Text file)
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long readLatestId()  {
        Long id = 1L;
        try(
        BufferedReader input = new BufferedReader(new FileReader(FILENAME))) {
            String last = null, line;
            while ((line = input.readLine()) != null) {
                last = line;
            }
            if(last == null) {
                id = 1L;
            } else{
                String[] lastLineArr = last.split("\\|");
                id = Long.valueOf(lastLineArr[0]);
                ++id;
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        return id;
    }

    public String getUrl(String longUrl) {
        LOGGER.info("Retrieving at {}", longUrl);
        String findUrl = null;
        try(FileInputStream inputStream =new FileInputStream(FILENAME);
            Scanner sc= new Scanner(inputStream, "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
                if(!line.equals("")) {
                    String[] foundUrl = line.split("\\|");
                    String appendUrl = foundUrl[3];
                    if(foundUrl.length > 4) {
                        int i = 4;
                        while(i < foundUrl.length){
                            appendUrl += '|'+foundUrl[i];
                            i++;
                        }
                    }
                        if (appendUrl.equals(longUrl)) {  // Stored longUrl on last position
                            findUrl = foundUrl[2];
                            break;
                        }
                    }
                }
            }
          catch (IOException ioException) {
              ioException.printStackTrace();
          }
        return findUrl;
    }
}


