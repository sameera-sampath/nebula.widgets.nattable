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
package org.eclipse.nebula.widgets.nattable.layer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.layer.CompositeLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.CompositeLayer.ChildLayerInfo;
import org.eclipse.nebula.widgets.nattable.layer.cell.LayerCell;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.test.fixture.layer.CompositeLayerFixture;
import org.eclipse.nebula.widgets.nattable.test.fixture.layer.DataLayerFixture;
import org.eclipse.nebula.widgets.nattable.test.fixture.layer.ViewportLayerFixture;
import org.eclipse.nebula.widgets.nattable.util.IClientAreaProvider;
import org.eclipse.swt.graphics.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * @see {@link CompositeLayerFixture} for the layout of columns/rows.
 */
public class CompositeLayerTest {

	private CompositeLayerFixture layerFixture;

	@Before
	public void setup() {
		layerFixture = new CompositeLayerFixture();
		layerFixture.bodyLayer.setClientAreaProvider(new IClientAreaProvider(){
			public Rectangle getClientArea() {
				return new Rectangle(0,0,160,100);
			}
		});
	}

	@Test
	public void testingChildLayerInfoForCornerByLayout() {
		ChildLayerInfo info = layerFixture.getChildLayerInfoByLayout(0,0);

		assertEquals(layerFixture.cornerLayer, info.getLayer());
		assertEquals(0, info.getColumnPositionOffset());
		assertEquals(0, info.getRowPositionOffset());
		assertEquals(0, info.getWidthOffset());
		assertEquals(0, info.getHeightOffset());

	}

	@Test
	public void testChildLayerInfoForViewportByLayout() {
		ChildLayerInfo info = layerFixture.getChildLayerInfoByLayout(1,1);

		assertEquals(layerFixture.bodyLayer, info.getLayer());
		assertEquals(5, info.getColumnPositionOffset());
		assertEquals(7, info.getRowPositionOffset());
		assertEquals(layerFixture.cornerLayer.getWidth(), info.getWidthOffset());
		assertEquals(layerFixture.cornerLayer.getHeight(), info.getHeightOffset());
	}

	@Test
	public void childLayerInfoByPixelPosition() throws Exception {
		ILayer layer = layerFixture.getChildLayerByXY(30, 40);
		assertEquals(layerFixture.bodyLayer, layer);
	}

	@Test
	public void getChildLayerByLayoutPosition() throws Exception {
		assertIsCorner(layerFixture.getChildLayerByLayoutCoordinate(0, 0));
		assertIsColHeader(layerFixture.getChildLayerByLayoutCoordinate(1, 0));
		assertIsRowHeader(layerFixture.getChildLayerByLayoutCoordinate(0, 1));
		assertIsBody(layerFixture.getChildLayerByLayoutCoordinate(1, 1));
	}

	/*
	 * Column specific tests
	 */

	@Test
	public void getColumnCount() throws Exception {
		assertEquals(10,layerFixture.getColumnCount());
	}

	@Test
	public void getColumnIndexByPosition() throws Exception {
		assertEquals(0,layerFixture.getColumnIndexByPosition(0));
		assertEquals(1,layerFixture.getColumnIndexByPosition(1));
		assertEquals(4,layerFixture.getColumnIndexByPosition(4));

		assertEquals(0,layerFixture.getColumnIndexByPosition(5));
		assertEquals(1,layerFixture.getColumnIndexByPosition(6));
		//Non existent col position
		assertEquals(-1,layerFixture.getColumnIndexByPosition(12));
	}

	@Test
	public void getWidth() throws Exception {
		assertEquals(150, layerFixture.getWidth());

		//20 columns total - 100 wide each
		layerFixture.setChildLayer(GridRegion.CORNER, new DataLayerFixture(10,10, 100, 20), 0, 0);
		layerFixture.setChildLayer(GridRegion.COLUMN_HEADER, new DataLayerFixture(10,10, 100, 20), 1, 0);
		layerFixture.populateChildLayerInfos();

		assertEquals(2000, layerFixture.getWidth());
	}

	@Test
	public void getColumnWidthByPosition() throws Exception {
		assertEquals(5, layerFixture.getColumnWidthByPosition(0));
		assertEquals(5, layerFixture.getColumnWidthByPosition(4));
		assertEquals(25, layerFixture.getColumnWidthByPosition(5));
		assertEquals(25, layerFixture.getColumnWidthByPosition(8));
		//Non existent
		assertEquals(-1, layerFixture.getColumnWidthByPosition(15));
	}

	@Test
	public void isColumnPositionResizable() throws Exception {
		assertTrue(layerFixture.isColumnPositionResizable(5));
		//Non existent
		assertFalse(layerFixture.isColumnPositionResizable(15));

		layerFixture.colHeaderLayer.setColumnPositionResizable(0, false);
		assertFalse(layerFixture.isColumnPositionResizable(5));
	}

	@Test
	public void getColumnPositionByX() throws Exception {
		assertEquals(0, layerFixture.getColumnPositionByX(0));
		assertEquals(0, layerFixture.getColumnPositionByX(4));
		assertEquals(1, layerFixture.getColumnPositionByX(5));
		assertEquals(1, layerFixture.getColumnPositionByX(9));
		assertEquals(2, layerFixture.getColumnPositionByX(10));
		//Non existent
		assertEquals(-1, layerFixture.getColumnPositionByX(200));
	}

	@Test
	public void getStartXOfColumnPosition() throws Exception {
		assertEquals(0, layerFixture.getStartXOfColumnPosition(0));
		assertEquals(5, layerFixture.getStartXOfColumnPosition(1));
		assertEquals(-1, layerFixture.getStartXOfColumnPosition(12));
	}

	/*
	 * Row specific tests
	 */
	@Test
	public void getRowCount() throws Exception {
		assertEquals(14, layerFixture.getRowCount());
	}

	@Test
	public void getRowIndexByPosition() throws Exception {
		assertEquals(0,layerFixture.getRowIndexByPosition(0));
		assertEquals(1,layerFixture.getRowIndexByPosition(1));
		assertEquals(4,layerFixture.getRowIndexByPosition(4));

		assertEquals(0,layerFixture.getRowIndexByPosition(7));
		assertEquals(1,layerFixture.getRowIndexByPosition(8));
		//Non existent row position
		assertEquals(-1,layerFixture.getRowIndexByPosition(20));
	}

	@Test
	public void getHeight() throws Exception {
		assertEquals(70, layerFixture.getHeight());

		//20 rows, each 20 high
		layerFixture.setChildLayer(GridRegion.CORNER, new DataLayerFixture(10,10, 100, 20), 0, 0);
		layerFixture.setChildLayer(GridRegion.ROW_HEADER, new DataLayerFixture(10,10, 100, 20), 0, 1);
		layerFixture.populateChildLayerInfos();

		assertEquals(400, layerFixture.getHeight());
	}

	@Test
	public void getRowHeightByPosition() throws Exception {
		assertEquals(5, layerFixture.getRowHeightByPosition(0));
		assertEquals(5, layerFixture.getRowHeightByPosition(4));
		assertEquals(5, layerFixture.getRowHeightByPosition(5));
		assertEquals(5, layerFixture.getRowHeightByPosition(8));
		//Non existent
		assertEquals(-1, layerFixture.getRowHeightByPosition(20));
	}

	@Test
	public void isRowPositionResizable() throws Exception {
		assertTrue(layerFixture.isRowPositionResizable(7));
		//Non existent
		assertFalse(layerFixture.isRowPositionResizable(20));

		layerFixture.rowHeaderLayer.setRowPositionResizable(0, false);
		assertFalse(layerFixture.isRowPositionResizable(7));
	}

	@Test
	public void getRowPositionByY() throws Exception {
		assertEquals(0, layerFixture.getRowPositionByY(0));
		assertEquals(0, layerFixture.getRowPositionByY(4));
		assertEquals(1, layerFixture.getRowPositionByY(5));
		assertEquals(1, layerFixture.getRowPositionByY(9));
		assertEquals(2, layerFixture.getRowPositionByY(10));
		//Non existent
		assertEquals(-1, layerFixture.getRowPositionByY(200));
	}

	@Test
	public void getStartYOfRowPosition() throws Exception {
		assertEquals(0, layerFixture.getStartYOfRowPosition(0));
		assertEquals(5, layerFixture.getStartYOfRowPosition(1));
		assertEquals(50, layerFixture.getStartYOfRowPosition(10));
		//Non existent
		assertEquals(-1, layerFixture.getStartYOfRowPosition(20));
	}

	@Test
	public void getCellBounds() throws Exception {
		Rectangle cellBounds = layerFixture.getCellBounds(0, 0);
		assertEquals(0, cellBounds.x);
		assertEquals(0, cellBounds.y);
		assertEquals(5, cellBounds.height);
		assertEquals(5, cellBounds.width);

		cellBounds = layerFixture.getCellBounds(6, 6);
		assertEquals(50, cellBounds.x);
		assertEquals(30, cellBounds.y);
		assertEquals(5, cellBounds.height);
		assertEquals(25, cellBounds.width);
	}

	@Test
	public void cellBoundsForNonExistentCellPosition() throws Exception {
		Rectangle cellBounds = layerFixture.getCellBounds(20, 20);
		assertEquals(-1, cellBounds.x);
		assertEquals(-1, cellBounds.y);
		assertEquals(-1, cellBounds.height);
		assertEquals(-1, cellBounds.width);
	}

	@Test
	public void getDataValueByPosition() throws Exception {
		assertEquals("[0, 0]",layerFixture.getDataValueByPosition(0, 0).toString());
		assertEquals("[0, 1]",layerFixture.getDataValueByPosition(0, 8).toString());
		assertEquals("[3, 0]",layerFixture.getDataValueByPosition(8, 0).toString());
	}

	@Test
	public void getDataValueForPositionNotInTheViewport() throws Exception {
		assertEquals("-1", layerFixture.getDataValueByPosition(12, 8).toString());
	}

	@Test
	public void getUnderlyingLayersByColumnPosition() throws Exception {
		Collection<ILayer> underlyingLayers = layerFixture.getUnderlyingLayersByColumnPosition(5);

		assertEquals(2, underlyingLayers.size());

		List<String> classNames = new ArrayList<String>();
		for (Iterator<ILayer> iterator = underlyingLayers.iterator(); iterator.hasNext();) {
			ILayer iLayer = iterator.next();
			classNames.add(iLayer.getClass().getSimpleName());
		}
		assertTrue(classNames.contains("DataLayerFixture"));
		assertTrue(classNames.contains("ViewportLayerFixture"));
	}

	@Test
	public void getCellByPosition() throws Exception {
		LayerCell cell = layerFixture.getCellByPosition(3, 2);
		assertIsCorner(cell.getLayer());
		assertEquals("[3, 2]", cell.getDataValue());
		assertEquals(new Rectangle(15, 10, 5, 5), cell.getBounds()); // pixel values
		assertEquals(DisplayMode.NORMAL, cell.getDisplayMode());

		// Get a cell from the body
		cell = layerFixture.getCellByPosition(8, 8);
		assertTrue(cell.getLayer() instanceof CompositeLayer);
		assertEquals("[3, 1]", cell.getDataValue());
		assertEquals(new Rectangle(310, 75, 100, 70), cell.getBounds());
		assertEquals(DisplayMode.NORMAL, cell.getDisplayMode());
		assertEquals(8, cell.getOriginColumnPosition());
		assertEquals(8, cell.getOriginRowPosition());
	}

	@Test
	public void getBoundsByPosition() throws Exception {
		Rectangle rowHeaderCellBounds = layerFixture.getBoundsByPosition(1, 10);
		assertEquals(new Rectangle(10, 50, 10, 5), rowHeaderCellBounds);
	}

	/*
	 * The following methods probe the underlying DataLayerFixture to ensure
	 * that we got the right one.
	 */
	private void assertIsBody(ILayer bodyLayer) {
		assertTrue(bodyLayer instanceof ViewportLayerFixture);
	}

	private void assertIsRowHeader(ILayer rowHeaderLayer) {
		assertEquals(10, rowHeaderLayer.getColumnWidthByPosition(0));
	}

	private void assertIsColHeader(ILayer colHeaderLayer) {
		assertEquals(25, colHeaderLayer.getColumnWidthByPosition(0));
	}

	private void assertIsCorner(ILayer cornerLayer) {
		assertEquals(5, cornerLayer.getColumnWidthByPosition(0));
	}
}