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

package com.josdem.jmetadata.util;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.josdem.jmetadata.ApplicationConstants;
import com.josdem.jmetadata.service.ImageService;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@Slf4j
class ImageUtilsTest {

  private static final Integer THREE_HUNDRED = 300;

  private ImageUtils imageUtils;

  @Mock private ImageService imageService;
  @Mock private Image image;
  @Mock private File file;
  @Mock private FileUtils fileUtils;
  @Mock private File root;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
    imageUtils = new ImageUtils(imageService, fileUtils);
  }

  @Test
  @DisplayName("saving cover art to file")
  void shouldSaveCoverArtToFile(TestInfo testInfo) throws Exception {
    log.info(testInfo.getDisplayName());
    when(imageService.createTempFile()).thenReturn(file);
    when(image.getHeight(isA(ImageObserver.class))).thenReturn(300);

    imageUtils.saveCoverArtToFile(image);

    verify(imageService).write(image, file);
  }

  @Test
  @DisplayName("not saving cover art if no image")
  void shouldNotSaveCoverArtIfNoImage(TestInfo testInfo) throws Exception {
    log.info(testInfo.getDisplayName());
    imageUtils.saveCoverArtToFile(null);

    verify(imageService, never()).createTempFile();
  }

  @Test
  @DisplayName("not saving cover art if no root file")
  void shouldNotSaveCoverArtIfRootAndNoImage(TestInfo testInfo) throws Exception {
    log.info(testInfo.getDisplayName());
    imageUtils.saveCoverArtToFile(null, file, StringUtils.EMPTY);

    verify(fileUtils, never()).createFile(file, StringUtils.EMPTY, ApplicationConstants.IMAGE_EXT);
  }

  @Test
  @DisplayName("saving cover art to file with custom prefix and path")
  void shouldSaveImageToFile() throws Exception {
    var prefix = "MIRI_";
    var path = "PATH";
    when(fileUtils.createFile(root, prefix, ApplicationConstants.IMAGE_EXT)).thenReturn(file);
    when(file.getAbsolutePath()).thenReturn(path);
    when(image.getHeight(isA(ImageObserver.class))).thenReturn(THREE_HUNDRED);

    imageUtils.saveCoverArtToFile(image, root, prefix);

    verify(fileUtils).createFile(root, prefix, ApplicationConstants.IMAGE_EXT);
  }
}
