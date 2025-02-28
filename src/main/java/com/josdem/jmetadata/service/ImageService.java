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

package com.josdem.jmetadata.service;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public interface ImageService {
  File createTempFile() throws IOException;

  void write(Image bufferedImage, File file) throws IOException;

  Image readImage(String imageURL) throws MalformedURLException, IOException;
}
