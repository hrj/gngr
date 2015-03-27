/*
    GNU LESSER GENERAL PUBLIC LICENSE
    Copyright (C) 2015 Uproot Labs India Pvt Ltd

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

 */

package org.lobobrowser.html.domimpl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.w3c.dom.html.HTMLElement;

public class HTMLCanvasElementImpl extends HTMLAbstractUIElement implements HTMLElement {

  public HTMLCanvasElementImpl() {
    super("CANVAS");
  }

  public String getHeight() {
    final UINode r = this.uiNode;
    final int height = r == null ? 0 : r.getBounds().height;
    return String.valueOf(height);
  }

  public void setHeight(final String height) {
    this.setAttribute("height", height);
  }

  public String getWidth() {
    final UINode r = this.uiNode;
    final int width = r == null ? 0 : r.getBounds().width;
    return String.valueOf(width);
  }

  public void setWidth(final String width) {
    this.setAttribute("width", width);
  }

  private java.awt.Image image = null;

  public void paintComponent(Graphics g) {
    if (image != null) {
      g.drawImage(image, 0, 0, null);
    }
  }

  public void setBounds(int width, int height) {
    if (image == null) {
      image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      drawGrid();
    } else if (image.getWidth(null) != width || image.getHeight(null) != height) {
      image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      drawGrid();
    }
  }

  private static final Color gridColor = new Color(30, 30, 30, 30);
  private static final int GRID_SIZE = 10;

  private void drawGrid() {
    Graphics2D g2 = (Graphics2D) image.getGraphics();
    final int height = image.getHeight(null);
    final int width = image.getWidth(null);

    g2.setColor(gridColor);

    for (int i = 0; i < height; i += GRID_SIZE) {
      g2.drawLine(0, i, width, i);
    }

    for (int i = 0; i < width; i += 10) {
      g2.drawLine(i, 0, i, height);
    }
  }

}
