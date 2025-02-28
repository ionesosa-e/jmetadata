/*
   Copyright 2025 Jose Morales contact@josdem.io

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.josdem.jmetadata.helper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@SpringBootTest
@ContextConfiguration(classes = {ApplicationContextSingleton.class, AudioFileHelper.class})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class AudioFileHelperTest {

  private final AudioFileHelper audioFileHelper;

  private final File pepeGarden =
      new File("src/test/resources/audio/Jaytech - Pepe Garden (Original Mix).mp3");

  @Test
  @DisplayName("reading audio file")
  public void shouldRead(TestInfo testInfo) throws Exception {
    log.info(testInfo.getDisplayName());
    assertNotNull(audioFileHelper.read(pepeGarden), "should read audio file");
  }
}
