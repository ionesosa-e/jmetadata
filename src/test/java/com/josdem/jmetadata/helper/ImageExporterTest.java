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

import static org.mockito.Mockito.*;

import com.josdem.jmetadata.model.ExportPackage;
import com.josdem.jmetadata.model.Metadata;
import com.josdem.jmetadata.service.MetadataService;
import com.josdem.jmetadata.util.ImageUtils;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ImageExporterTest {
  private ImageExporter imageExporter;

  @Mock private ImageUtils imageUtils;
  @Mock private Metadata metadata;
  @Mock private Image coverArt;
  @Mock private MetadataService metadataService;

  private final List<Metadata> metadataList = new ArrayList<>();
  private ExportPackage exportPackage;
  @Mock private File root;

  @BeforeEach
  public void setup() throws Exception {
    var title = "Bliksem";
    var album = "Bliksem Album";
    var artist = "Sander van Doorn";
    MockitoAnnotations.openMocks(this);
    when(metadata.getAlbum()).thenReturn(album);
    when(metadata.getArtist()).thenReturn(artist);
    when(metadata.getTitle()).thenReturn(title);
    metadataList.add(metadata);
    exportPackage = new ExportPackage(root, metadataList);
    imageExporter = new ImageExporter(imageUtils, metadataService);
  }

  @Test
  public void shouldExportASingleImage() throws Exception {
    when(metadata.getCoverArt()).thenReturn(coverArt);
    when(metadataService.isSameAlbum(metadataList)).thenReturn(true);
    imageExporter.export(exportPackage);
    verify(imageUtils)
        .saveCoverArtToFile(metadataList.get(0).getCoverArt(), root, StringUtils.EMPTY);
  }

  @Test
  public void shouldExportASingleImageWhenSameAlbum() throws Exception {
    when(metadata.getCoverArt()).thenReturn(coverArt);
    when(metadataService.isSameAlbum(metadataList)).thenReturn(true);
    metadataList.add(metadata);
    imageExporter.export(exportPackage);
    verify(imageUtils)
        .saveCoverArtToFile(metadataList.get(0).getCoverArt(), root, StringUtils.EMPTY);
  }

  @Test
  public void shouldExportTwoImagesWhenDifAlbum() throws Exception {
    when(metadata.getCoverArt()).thenReturn(coverArt);
    Metadata metadata = setSecondMetadataExpectations();
    metadataList.add(metadata);
    imageExporter.export(exportPackage);
    verify(imageUtils).saveCoverArtToFile(coverArt, root, "Sander van Doorn" + "-" + "Bliksem");
    verify(imageUtils)
        .saveCoverArtToFile(coverArt, root, "ATA" + "-" + "Blue Skies (Andy Tau Remix)");
  }

  private Metadata setSecondMetadataExpectations() {
    Metadata metadata = mock(Metadata.class);
    when(metadata.getAlbum()).thenReturn("Blue Skies");
    when(metadata.getArtist()).thenReturn("ATA");
    when(metadata.getTitle()).thenReturn("Blue Skies (Andy Tau Remix)");
    when(metadata.getCoverArt()).thenReturn(coverArt);
    return metadata;
  }

  @Test
  public void shouldNotExportIfNoImage() throws Exception {
    imageExporter.export(exportPackage);
    verify(imageUtils, never())
        .saveCoverArtToFile(metadataList.get(0).getCoverArt(), root, StringUtils.EMPTY);
  }
}
