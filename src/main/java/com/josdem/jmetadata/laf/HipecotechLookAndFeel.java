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

package com.josdem.jmetadata.laf;

import java.text.ParseException;
import javax.swing.plaf.synth.SynthLookAndFeel;

public class HipecotechLookAndFeel extends SynthLookAndFeel {
  private static final long serialVersionUID = -4532847419431106496L;

  public HipecotechLookAndFeel() throws ParseException {
    load(
        HipecotechLookAndFeel.class.getResourceAsStream("/style/Hipecotech.xml"),
        HipecotechLookAndFeel.class);
  }

  @Override
  public String getName() {
    return "Hipecotech";
  }

  @Override
  public String getID() {
    return "Hipecotech";
  }

  public String getDescription() {
    return "Hipecotech Look and Feel";
  }
}
