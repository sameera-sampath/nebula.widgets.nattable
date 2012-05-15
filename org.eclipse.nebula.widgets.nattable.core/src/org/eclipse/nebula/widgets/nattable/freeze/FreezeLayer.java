/*******************************************************************************
 * Copyright (c) 2012 Original authors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.nebula.widgets.nattable.freeze;

import org.eclipse.nebula.widgets.nattable.coordinate.PositionCoordinate;
import org.eclipse.nebula.widgets.nattable.freeze.event.FreezeEventHandler;
import org.eclipse.nebula.widgets.nattable.layer.AbstractLayerTransform;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.IUniqueIndexLayer;
import org.eclipse.nebula.widgets.nattable.layer.LayerUtil;

public class FreezeLayer extends AbstractLayerTransform implements IUniqueIndexLayer {

	private PositionCoordinate topLeftPosition = new PositionCoordinate(this, -1, -1);

	private PositionCoordinate bottomRightPosition = new PositionCoordinate(this, -1, -1);

	public FreezeLayer(IUniqueIndexLayer underlyingLayer) {
		super(underlyingLayer);

		registerEventHandler(new FreezeEventHandler(this));
	}
	
	public boolean isFrozen() {
		return getColumnCount() > 0 || getRowCount() > 0;
	}

	// Coordinates

	public PositionCoordinate getTopLeftPosition() {
		return topLeftPosition;
	}

	public void setTopLeftPosition(int leftColumnPosition, int topRowPosition) {
		this.topLeftPosition = new PositionCoordinate(this, leftColumnPosition, topRowPosition);
	}

	public PositionCoordinate getBottomRightPosition() {
		return bottomRightPosition;
	}

	public void setBottomRightPosition(int rightColumnPosition, int bottomRowPosition) {
		this.bottomRightPosition = new PositionCoordinate(this, rightColumnPosition, bottomRowPosition);
	}

	// Column features

	@Override
	public int getColumnCount() {
		if (topLeftPosition.columnPosition >= 0 && bottomRightPosition.columnPosition >= 0) {
			return bottomRightPosition.columnPosition - topLeftPosition.columnPosition + 1;
		} else {
			return 0;
		}
	}

	@Override
	public int getPreferredColumnCount() {
		return getColumnCount();
	}

	public int getColumnPositionByIndex(int columnIndex) {
		IUniqueIndexLayer underlyingLayer = (IUniqueIndexLayer) getUnderlyingLayer();
		return underlyingToLocalColumnPosition(underlyingLayer, underlyingLayer.getColumnPositionByIndex(columnIndex));
	}

	@Override
	public int localToUnderlyingColumnPosition(int localColumnPosition) {
		return topLeftPosition.columnPosition + localColumnPosition;
	}

	@Override
	public int underlyingToLocalColumnPosition(ILayer sourceUnderlyingLayer, int underlyingColumnPosition) {
		return underlyingColumnPosition - topLeftPosition.columnPosition;
	}

	@Override
	public int getWidth() {
		int width = 0;
		for (int columnPosition = 0; columnPosition < getColumnCount(); columnPosition++) {
			width += getColumnWidthByPosition(columnPosition);
		}
		return width;
	}

	@Override
	public int getPreferredWidth() {
		return getWidth();
	}
	
	@Override
	public int getColumnPositionByX(int x) {
		int xOffset = underlyingLayer.getStartXOfColumnPosition(topLeftPosition.columnPosition);
		return underlyingToLocalColumnPosition(underlyingLayer, underlyingLayer.getColumnPositionByX(xOffset + x));
	}

	@Override
	public int getStartXOfColumnPosition(int columnPosition) {
		IUniqueIndexLayer underlyingLayer = (IUniqueIndexLayer) getUnderlyingLayer();
		final int underlyingColumnPosition = LayerUtil.convertColumnPosition(this, columnPosition, underlyingLayer);
		return underlyingLayer.getStartXOfColumnPosition(underlyingColumnPosition) - underlyingLayer.getStartXOfColumnPosition(topLeftPosition.columnPosition);
	}
	
	// Row features

	@Override
	public int getRowCount() {
		if (topLeftPosition.rowPosition >= 0 && bottomRightPosition.rowPosition >= 0) {
			int frozenRowCount = bottomRightPosition.rowPosition - topLeftPosition.rowPosition + 1;
			int underlyingRowCount = getUnderlyingLayer().getRowCount();
			return frozenRowCount <= underlyingRowCount ? frozenRowCount : 0;
		} else {
			return 0;
		}
	}

	@Override
	public int getPreferredRowCount() {
		return getRowCount();
	}

	public int getRowPositionByIndex(int rowIndex) {
		IUniqueIndexLayer underlyingLayer = (IUniqueIndexLayer) getUnderlyingLayer();
		return underlyingToLocalRowPosition(underlyingLayer, underlyingLayer.getRowPositionByIndex(rowIndex));
	}

	@Override
	public int localToUnderlyingRowPosition(int localRowPosition) {
		return topLeftPosition.rowPosition + localRowPosition;
	}

	@Override
	public int underlyingToLocalRowPosition(ILayer sourceUnderlyingLayer, int underlyingRowPosition) {
		return underlyingRowPosition - topLeftPosition.rowPosition;
	}

	@Override
	public int getHeight() {
		int height = 0;
		for (int rowPosition = 0; rowPosition < getRowCount(); rowPosition++) {
			height += getRowHeightByPosition(rowPosition);
		}
		return height;
	}

	@Override
	public int getPreferredHeight() {
		return getHeight();
	}
	
	@Override
	public int getRowPositionByY(int y) {
		int yOffset = underlyingLayer.getStartYOfRowPosition(topLeftPosition.rowPosition);
		return underlyingToLocalRowPosition(underlyingLayer, underlyingLayer.getRowPositionByY(yOffset + y));
	}

	@Override
	public int getStartYOfRowPosition(int rowPosition) {
		IUniqueIndexLayer underlyingLayer = (IUniqueIndexLayer) getUnderlyingLayer();
		final int underlyingRowPosition = LayerUtil.convertRowPosition(this, rowPosition, underlyingLayer);
		return underlyingLayer.getStartYOfRowPosition(underlyingRowPosition) - underlyingLayer.getStartYOfRowPosition(topLeftPosition.rowPosition);
	}

}