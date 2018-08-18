/*
   Copyright 2013 Jose Luis De la Cruz Morales joseluis.delacruz@gmail.com

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

package org.jas.helper;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.jas.ApplicationState;
import org.jas.service.ImageService;
import org.junit.Test;

public class TestImageHelper {

	private static final String PREFIX = "PREFIX";
	private ImageService imageService = new ImageService();

	@Test
	public void shouldCreateTempFile() throws Exception {
		File tempFile = imageService.createTempFile(StringUtils.EMPTY);
		assertTrue(tempFile.getName().contains(ApplicationState.PREFIX));
		assertTrue(tempFile.getName().contains(ApplicationState.IMAGE_EXT));
	}

	@Test
	public void shouldCreateTempFileWithCustomPrefix() throws Exception {
		File tempFile = imageService.createTempFile(PREFIX);
		assertTrue(tempFile.getName().contains(PREFIX));
		assertTrue(tempFile.getName().contains(ApplicationState.IMAGE_EXT));
	}

}
