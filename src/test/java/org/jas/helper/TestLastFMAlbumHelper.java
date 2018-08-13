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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.jas.helper.LastFMAlbumHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.umass.lastfm.Album;


public class TestLastFMAlbumHelper {
	private LastFMAlbumHelper lastFMAlbumHelper = new LastFMAlbumHelper();
	private Date releaseDate = new Date();
	@Mock
	private Album album;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldGetYear() throws Exception {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
		String currentYear = simpleDateformat.format(releaseDate);
		String year = lastFMAlbumHelper.getYear(releaseDate);
		assertEquals(currentYear, year);
	}

	@Test
	public void shouldGetEmptyYear() throws Exception {
		assertEquals(StringUtils.EMPTY, lastFMAlbumHelper.getYear(null));
	}

	@Test
	public void shouldMatchAGenre() throws Exception {
		Collection<String> tags = new ArrayList<String>();
		String tag = "House";
		tags.add(tag);

		when(album.getTags()).thenReturn(tags);

		String result = lastFMAlbumHelper.getGenre(album);
		assertEquals(tag, result);
	}

	@Test
	public void shouldNotMatchAGenre() throws Exception {
		Collection<String> tags = new ArrayList<String>();
		String tag = "usa";
		tags.add(tag);

		when(album.getTags()).thenReturn(tags);

		String result = lastFMAlbumHelper.getGenre(album);
		assertEquals(StringUtils.EMPTY, result);
	}
}
