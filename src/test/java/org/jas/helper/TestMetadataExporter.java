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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jas.ApplicationState;
import org.jas.helper.MetadataExporter;
import org.jas.helper.OutStreamWriter;
import org.jas.model.ExportPackage;
import org.jas.model.Metadata;
import org.jas.service.FormatterService;
import org.jas.service.MetadataService;
import org.jas.util.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestMetadataExporter {
	private static final String NEW_LINE = "\n";
	private static final String DASH = " - ";
	private static final String DOT = ". ";
	private static final String PAR_OPEN = " (";
	private static final String PAR_CLOSE = ")";
	private static final String BY = " by ";

	@InjectMocks
	private MetadataExporter metadataExporter = new MetadataExporter();

	@Mock
	private FileUtils fileUtils;
	@Mock
	private FormatterService formatter;
	@Mock
	private Metadata metadata;
	@Mock
	private File file;
	@Mock
	private OutStreamWriter outputStreamWriter;
	@Mock
	private OutputStream writer;
	@Mock
	private ExportPackage exportPackage;
	@Mock
	private MetadataService metadataService;

	private String album = "Bliksem";
	private String artist = "Sander van Doorn";
	private String title = "Bliksem";
	int lenght = 397;
	private String lenghtFormated = "6:37";
	private List<Metadata> metadatas = new ArrayList<Metadata>();

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(metadata.getAlbum()).thenReturn(album);
		when(metadata.getArtist()).thenReturn(artist);
		when(metadata.getTitle()).thenReturn(title);
		when(metadata.getLength()).thenReturn(lenght);
		when(formatter.getDuration(metadata.getLength())).thenReturn(lenghtFormated);
		metadatas.add(metadata);
		when(exportPackage.getRoot()).thenReturn(file);
		when(exportPackage.getMetadataList()).thenReturn(metadatas);
		when(fileUtils.createFile(file, StringUtils.EMPTY, ApplicationState.FILE_EXT)).thenReturn(file);
		when(outputStreamWriter.getWriter(file)).thenReturn(writer);
	}

	@Test
	public void shouldExport() throws Exception {
		when(metadataService.isSameAlbum(metadatas)).thenReturn(true);

		metadataExporter.export(exportPackage);

		verify(writer, times(2)).write(metadata.getAlbum().getBytes());
		verify(writer).write(BY.getBytes());
		verify(writer, times(2)).write(metadata.getArtist().getBytes());
		verify(writer, times(3)).write(NEW_LINE.getBytes());

		verify(metadataService).isSameAlbum(metadatas);
		verify(writer).write(Integer.toString(1).getBytes());
		verify(writer).write(DOT.getBytes());
		verify(writer).write(DASH.getBytes());
		verify(writer, times(2)).write(metadata.getTitle().getBytes());
		verify(writer).write(PAR_OPEN.getBytes());
		verify(writer).write(formatter.getDuration(metadata.getLength()).getBytes());
		verify(writer).write(PAR_CLOSE.getBytes());
	}
}
