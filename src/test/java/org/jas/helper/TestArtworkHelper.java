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

import static org.junit.Assert.assertNotNull;

import org.jas.helper.ArtworkHelper;
import org.jaudiotagger.tag.datatype.Artwork;
import org.junit.Test;

public class TestArtworkHelper {

	private ArtworkHelper artworkHelper = new ArtworkHelper();

	@Test
	public void shouldCreateAnArtWork() throws Exception {
		Artwork artwork = artworkHelper.createArtwork();
		assertNotNull(artwork);
	}

}
