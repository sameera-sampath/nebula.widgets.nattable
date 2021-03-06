/*******************************************************************************
 * Copyright (c) 2014 Dirk Fauth and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dirk Fauth <dirk.fauth@gmail.com> - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.nebula.widgets.nattable.extension.glazedlists.groupBy;

import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.painter.cell.BackgroundPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.eclipse.nebula.widgets.nattable.style.BorderStyle;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.style.IStyle;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.style.TextDecorationEnum;
import org.eclipse.nebula.widgets.nattable.style.VerticalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.style.theme.IThemeExtension;
import org.eclipse.nebula.widgets.nattable.style.theme.ThemeConfiguration;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * IThemeExtension that adds styling configurations for the GroupBy feature that comes with the GlazedLists extension.
 * <p>
 * Note: It is not possible to change the {@link ICellPainter} via this default theme configuration.
 * 		 The reason for this is that the implementation {@link GroupByHeaderPainter} contains
 * 		 several technical details that are necessary to make the GroupBy feature work as it is.
 * 		 Changing the painter could break the feature.
 * </p>
 * <p>
 * Note: Simply changing the font doesn't result in automatically resizing the GroupBy header height.
 * 		 This is the same as for all other layers too, as for calculation of the row height, the
 * 		 GC is necessary. To support also bigger fonts in the GroupBy header region, you are able
 * 		 to resize the GroupBy header manually like this:<br/>
 * <pre>
 * natTable.doCommand(new RowResizeCommand(groupByHeaderLayer, 0, 50));
 * </pre>
 * </p>
 * 
 * @author Dirk Fauth
 *
 */
public class DefaultGroupByThemeExtension implements IThemeExtension {

	// group by style
	
	public Color groupByBgColor 									= null;
	public Color groupByFgColor 									= null;
	public Color groupByGradientBgColor 							= null;
	public Color groupByGradientFgColor 							= null;
	public HorizontalAlignmentEnum groupByHAlign 					= null;
	public VerticalAlignmentEnum groupByVAlign 						= null;
	public Font groupByFont 										= null;
	public Image groupByImage 										= null;
	public BorderStyle groupByBorderStyle 							= null;
	public Character groupByPWEchoChar 								= null;
	public TextDecorationEnum groupByTextDecoration 				= null;

	// group by object style
	
	public Color groupByObjectBgColor 								= null;
	public Color groupByObjectFgColor 								= null;
	public Color groupByObjectGradientBgColor 						= null;
	public Color groupByObjectGradientFgColor 						= null;
	public HorizontalAlignmentEnum groupByObjectHAlign 				= null;
	public VerticalAlignmentEnum groupByObjectVAlign 				= null;
	public Font groupByObjectFont 									= null;
	public Image groupByObjectImage 								= null;
	public BorderStyle groupByObjectBorderStyle 					= null;
	public Character groupByObjectPWEchoChar 						= null;
	public TextDecorationEnum groupByObjectTextDecoration 			= null;
	
	public ICellPainter groupByObjectCellPainter 					= new BackgroundPainter(new GroupByCellTextPainter());

	// group by object style
	
	public Color groupByObjectSelectionBgColor 						= null;
	public Color groupByObjectSelectionFgColor 						= null;
	public Color groupByObjectSelectionGradientBgColor 				= null;
	public Color groupByObjectSelectionGradientFgColor 				= null;
	public HorizontalAlignmentEnum groupByObjectSelectionHAlign 	= null;
	public VerticalAlignmentEnum groupByObjectSelectionVAlign 		= null;
	public Font groupByObjectSelectionFont 							= null;
	public Image groupByObjectSelectionImage 						= null;
	public BorderStyle groupByObjectSelectionBorderStyle 			= null;
	public Character groupByObjectSelectionPWEchoChar 				= null;
	public TextDecorationEnum groupByObjectSelectionTextDecoration 	= null;
	
	public ICellPainter groupByObjectSelectionCellPainter 			= null;
	
	// group by summary style
	
	public Color groupBySummaryBgColor 								= null;
	public Color groupBySummaryFgColor 								= null;
	public Color groupBySummaryGradientBgColor 						= null;
	public Color groupBySummaryGradientFgColor 						= null;
	public HorizontalAlignmentEnum groupBySummaryHAlign 			= null;
	public VerticalAlignmentEnum groupBySummaryVAlign 				= null;
	public Font groupBySummaryFont 									= null;
	public Image groupBySummaryImage 								= null;
	public BorderStyle groupBySummaryBorderStyle 					= null;
	public Character groupBySummaryPWEchoChar 						= null;
	public TextDecorationEnum groupBySummaryTextDecoration 			= null;
	
	public ICellPainter groupBySummaryCellPainter 					= null;
	
	// group by summary selection style
	
	public Color groupBySummarySelectionBgColor 					= null;
	public Color groupBySummarySelectionFgColor 					= null;
	public Color groupBySummarySelectionGradientBgColor 			= null;
	public Color groupBySummarySelectionGradientFgColor 			= null;
	public HorizontalAlignmentEnum groupBySummarySelectionHAlign 	= null;
	public VerticalAlignmentEnum groupBySummarySelectionVAlign 		= null;
	public Font groupBySummarySelectionFont 						= null;
	public Image groupBySummarySelectionImage 						= null;
	public BorderStyle groupBySummarySelectionBorderStyle 			= null;
	public Character groupBySummarySelectionPWEchoChar 				= null;
	public TextDecorationEnum groupBySummarySelectionTextDecoration = null;
	
	public ICellPainter groupBySummarySelectionCellPainter 			= null;

	// group by hint style
	
	public String groupByHint 										= null;

	public Color groupByHintBgColor 								= null;
	public Color groupByHintFgColor 								= null;
	public Color groupByHintGradientBgColor 						= null;
	public Color groupByHintGradientFgColor 						= null;
	public HorizontalAlignmentEnum groupByHintHAlign 				= null;
	public VerticalAlignmentEnum groupByHintVAlign 					= null;
	public Font groupByHintFont 									= null;
	public Image groupByHintImage 									= null;
	public BorderStyle groupByHintBorderStyle 						= null;
	public Character groupByHintPWEchoChar 							= null;
	public TextDecorationEnum groupByHintTextDecoration				= null;

	@Override
	public void registerStyles(IConfigRegistry configRegistry) {
		configureGroupByStyle(configRegistry);
		configureGroupByObjectStyle(configRegistry);
		configureGroupByObjectSelectionStyle(configRegistry);
		configureGroupBySummaryStyle(configRegistry);
		configureGroupBySummarySelectionStyle(configRegistry);
		configureGroupByHint(configRegistry);
	}

	/**
	 * Registering the style configuration for the GroupBy header region.
	 * Note that it is not possible to exchange the ICellPainter that is used to
	 * render the GroupBy header region, as it contains a lot of internal code.
	 * @param configRegistry The IConfigRegistry that is used by the NatTable instance
	 * 			to which the style configuration should be applied to.
	 */
	protected void configureGroupByStyle(IConfigRegistry configRegistry) {
		IStyle groupByStyle = getGroupByStyle();
		if (!ThemeConfiguration.isStyleEmpty(groupByStyle)) {
			configRegistry.registerConfigAttribute(
					CellConfigAttributes.CELL_STYLE,
					groupByStyle,
					DisplayMode.NORMAL,
					GroupByHeaderLayer.GROUP_BY_REGION);
		}
	}
	
	/**
	 * Returns the {@link IStyle} that should be used to render the GroupBy region in a NatTable.
	 * <p>
	 * That means this {@link IStyle} is registered against {@link DisplayMode#NORMAL}
	 * and the configuration label {@link GroupByHeaderLayer#GROUP_BY_REGION}.
	 * </p>
	 * <p>
	 * If this method returns <code>null</code>, no value will be registered to keep the
	 * IConfigRegistry clean. The result would be the same, as if no value is found in the
	 * IConfigRegistry. In this case the rendering will fallback to the default configuration.
	 * </p>
	 * @return The {@link IStyle} that should be used to render the GroupBy region in a NatTable. 
	 */
	protected IStyle getGroupByStyle() {
		IStyle cellStyle = new Style();
		cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, groupByBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, groupByFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_BACKGROUND_COLOR, groupByGradientBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_FOREGROUND_COLOR, groupByGradientFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, groupByHAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, groupByVAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.FONT, groupByFont);
		cellStyle.setAttributeValue(CellStyleAttributes.IMAGE, groupByImage);
		cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, groupByBorderStyle);
		cellStyle.setAttributeValue(CellStyleAttributes.PASSWORD_ECHO_CHAR, groupByPWEchoChar);
		cellStyle.setAttributeValue(CellStyleAttributes.TEXT_DECORATION, groupByTextDecoration);
		return cellStyle;
	}

	/**
	 * Registering the style configuration for the GroupBy objects.
	 * @param configRegistry The IConfigRegistry that is used by the NatTable instance
	 * 			to which the style configuration should be applied to.
	 */
	protected void configureGroupByObjectStyle(IConfigRegistry configRegistry) {
		IStyle groupByObjectStyle = getGroupByObjectStyle();
		if (!ThemeConfiguration.isStyleEmpty(groupByObjectStyle)) {
			configRegistry.registerConfigAttribute(
					CellConfigAttributes.CELL_STYLE,
					groupByObjectStyle,
					DisplayMode.NORMAL,
					GroupByDataLayer.GROUP_BY_OBJECT);
		}
		
		ICellPainter cellPainter = getGroupByObjectCellPainter();
		if (cellPainter != null) {
			configRegistry.registerConfigAttribute(
					CellConfigAttributes.CELL_PAINTER, 
					cellPainter, 
					DisplayMode.NORMAL, 
					GroupByDataLayer.GROUP_BY_OBJECT);
		}
	}
	
	/**
	 * Returns the {@link IStyle} that should be used to render the GroupBy object rows in a NatTable.
	 * <p>
	 * That means this {@link IStyle} is registered against {@link DisplayMode#NORMAL}
	 * and the configuration label {@link GroupByDataLayer#GROUP_BY_OBJECT}.
	 * </p>
	 * <p>
	 * If this method returns <code>null</code>, no value will be registered to keep the
	 * IConfigRegistry clean. The result would be the same, as if no value is found in the
	 * IConfigRegistry. In this case the rendering will fallback to the default configuration.
	 * </p>
	 * @return The {@link IStyle} that should be used to render the GroupBy object rows in a NatTable. 
	 */
	protected IStyle getGroupByObjectStyle() {
		IStyle cellStyle = new Style();
		cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, groupByObjectBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, groupByObjectFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_BACKGROUND_COLOR, groupByObjectGradientBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_FOREGROUND_COLOR, groupByObjectGradientFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, groupByObjectHAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, groupByObjectVAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.FONT, groupByObjectFont);
		cellStyle.setAttributeValue(CellStyleAttributes.IMAGE, groupByObjectImage);
		cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, groupByObjectBorderStyle);
		cellStyle.setAttributeValue(CellStyleAttributes.PASSWORD_ECHO_CHAR, groupByObjectPWEchoChar);
		cellStyle.setAttributeValue(CellStyleAttributes.TEXT_DECORATION, groupByObjectTextDecoration);
		return cellStyle;
	}
	
	/**
	 * Returns the {@link ICellPainter} that should be used to render the GroupBy object row cells in a NatTable.
	 * Typically you should use a {@link GroupByCellTextPainter} is most inner cell painter, as it is
	 * configured to only render group by content like group by summary and tree cells.
	 * <p>
	 * That means this {@link ICellPainter} is registered against {@link DisplayMode#NORMAL}
	 * and the configuration label {@link GroupByDataLayer#GROUP_BY_OBJECT}.
	 * </p>
	 * <p>
	 * If this method returns <code>null</code>, no value will be registered to keep the
	 * IConfigRegistry clean. The result would be the same, as if no value is found in the
	 * IConfigRegistry. In this case the rendering will fallback to the default configuration.
	 * </p>
	 * @return The {@link ICellPainter} that should be used to render the GroupBy object row cells in a NatTable.
	 */
	protected ICellPainter getGroupByObjectCellPainter() {
		return this.groupByObjectCellPainter;
	}

	/**
	 * Registering the style configuration for the selected GroupBy objects.
	 * @param configRegistry The IConfigRegistry that is used by the NatTable instance
	 * 			to which the style configuration should be applied to.
	 */
	protected void configureGroupByObjectSelectionStyle(IConfigRegistry configRegistry) {
		IStyle groupByObjectStyle = getGroupByObjectSelectionStyle();
		if (!ThemeConfiguration.isStyleEmpty(groupByObjectStyle)) {
			configRegistry.registerConfigAttribute(
					CellConfigAttributes.CELL_STYLE,
					groupByObjectStyle,
					DisplayMode.SELECT,
					GroupByDataLayer.GROUP_BY_OBJECT);
		}
		
		ICellPainter cellPainter = getGroupByObjectSelectionCellPainter();
		if (cellPainter != null) {
			configRegistry.registerConfigAttribute(
					CellConfigAttributes.CELL_PAINTER, 
					cellPainter, 
					DisplayMode.SELECT, 
					GroupByDataLayer.GROUP_BY_OBJECT);
		}
	}
	
	/**
	 * Returns the {@link IStyle} that should be used to render the selected GroupBy object rows in a NatTable.
	 * <p>
	 * That means this {@link IStyle} is registered against {@link DisplayMode#SELECT}
	 * and the configuration label {@link GroupByDataLayer#GROUP_BY_OBJECT}.
	 * </p>
	 * <p>
	 * If this method returns <code>null</code>, no value will be registered to keep the
	 * IConfigRegistry clean. The result would be the same, as if no value is found in the
	 * IConfigRegistry. In this case the rendering will fallback to the default configuration.
	 * </p>
	 * @return The {@link IStyle} that should be used to render the selected GroupBy object rows in a NatTable. 
	 */
	protected IStyle getGroupByObjectSelectionStyle() {
		IStyle cellStyle = new Style();
		cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, groupByObjectSelectionBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, groupByObjectSelectionFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_BACKGROUND_COLOR, groupByObjectSelectionGradientBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_FOREGROUND_COLOR, groupByObjectSelectionGradientFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, groupByObjectSelectionHAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, groupByObjectSelectionVAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.FONT, groupByObjectSelectionFont);
		cellStyle.setAttributeValue(CellStyleAttributes.IMAGE, groupByObjectSelectionImage);
		cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, groupByObjectSelectionBorderStyle);
		cellStyle.setAttributeValue(CellStyleAttributes.PASSWORD_ECHO_CHAR, groupByObjectSelectionPWEchoChar);
		cellStyle.setAttributeValue(CellStyleAttributes.TEXT_DECORATION, groupByObjectSelectionTextDecoration);
		return cellStyle;
	}
	
	/**
	 * Returns the {@link ICellPainter} that should be used to render the selected GroupBy object row 
	 * cells in a NatTable. Typically you should use a {@link GroupByCellTextPainter} is most inner cell 
	 * painter, as it is configured to only render group by content like group by summary and tree cells.
	 * <p>
	 * That means this {@link ICellPainter} is registered against {@link DisplayMode#SELECT}
	 * and the configuration label {@link GroupByDataLayer#GROUP_BY_OBJECT}.
	 * </p>
	 * <p>
	 * If this method returns <code>null</code>, no value will be registered to keep the
	 * IConfigRegistry clean. The result would be the same, as if no value is found in the
	 * IConfigRegistry. In this case the rendering will fallback to the default configuration.
	 * </p>
	 * @return The {@link ICellPainter} that should be used to render the selected GroupBy object row cells 
	 * 			in a NatTable.
	 */
	protected ICellPainter getGroupByObjectSelectionCellPainter() {
		return this.groupByObjectSelectionCellPainter;
	}

	/**
	 * Registering the style configuration for the GroupBy summary.
	 * @param configRegistry The IConfigRegistry that is used by the NatTable instance
	 * 			to which the style configuration should be applied to.
	 */
	protected void configureGroupBySummaryStyle(IConfigRegistry configRegistry) {
		IStyle groupBySummaryStyle = getGroupBySummaryStyle();
		if (!ThemeConfiguration.isStyleEmpty(groupBySummaryStyle)) {
			configRegistry.registerConfigAttribute(
					CellConfigAttributes.CELL_STYLE,
					groupBySummaryStyle,
					DisplayMode.NORMAL,
					GroupByDataLayer.GROUP_BY_SUMMARY);
		}
		
		ICellPainter cellPainter = getGroupBySummaryCellPainter();
		if (cellPainter != null) {
			configRegistry.registerConfigAttribute(
					CellConfigAttributes.CELL_PAINTER, 
					cellPainter, 
					DisplayMode.NORMAL, 
					GroupByDataLayer.GROUP_BY_SUMMARY);
		}
	}
	
	/**
	 * Returns the {@link IStyle} that should be used to render the GroupBy row summary cells in a NatTable.
	 * <p>
	 * That means this {@link IStyle} is registered against {@link DisplayMode#NORMAL}
	 * and the configuration label {@link GroupByDataLayer#GROUP_BY_SUMMARY}.
	 * </p>
	 * <p>
	 * If this method returns <code>null</code>, no value will be registered to keep the
	 * IConfigRegistry clean. The result would be the same, as if no value is found in the
	 * IConfigRegistry. In this case the rendering will fallback to the default configuration.
	 * </p>
	 * @return The {@link IStyle} that should be used to render the GroupBy summary row cells in a NatTable. 
	 */
	protected IStyle getGroupBySummaryStyle() {
		IStyle cellStyle = new Style();
		cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, groupBySummaryBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, groupBySummaryFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_BACKGROUND_COLOR, groupBySummaryGradientBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_FOREGROUND_COLOR, groupBySummaryGradientFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, groupBySummaryHAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, groupBySummaryVAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.FONT, groupBySummaryFont);
		cellStyle.setAttributeValue(CellStyleAttributes.IMAGE, groupBySummaryImage);
		cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, groupBySummaryBorderStyle);
		cellStyle.setAttributeValue(CellStyleAttributes.PASSWORD_ECHO_CHAR, groupBySummaryPWEchoChar);
		cellStyle.setAttributeValue(CellStyleAttributes.TEXT_DECORATION, groupBySummaryTextDecoration);
		return cellStyle;
	}
	
	/**
	 * Returns the {@link ICellPainter} that should be used to render the GroupBy row summary cells in a NatTable.
	 * Typically you should use a {@link GroupByCellTextPainter} is most inner cell painter, as it is
	 * configured to only render group by content like group by summary and tree cells.
	 * <p>
	 * That means this {@link ICellPainter} is registered against {@link DisplayMode#NORMAL}
	 * and the configuration label {@link GroupByDataLayer#GROUP_BY_SUMMARY}.
	 * </p>
	 * <p>
	 * If this method returns <code>null</code>, no value will be registered to keep the
	 * IConfigRegistry clean. The result would be the same, as if no value is found in the
	 * IConfigRegistry. In this case the rendering will fallback to the default configuration.
	 * </p>
	 * @return The {@link ICellPainter} that should be used to render the GroupBy row summary cells in a NatTable.
	 */
	protected ICellPainter getGroupBySummaryCellPainter() {
		return this.groupBySummaryCellPainter;
	}

	/**
	 * Registering the style configuration for the selected GroupBy summary.
	 * @param configRegistry The IConfigRegistry that is used by the NatTable instance
	 * 			to which the style configuration should be applied to.
	 */
	protected void configureGroupBySummarySelectionStyle(IConfigRegistry configRegistry) {
		IStyle groupBySummaryStyle = getGroupBySummarySelectionStyle();
		if (!ThemeConfiguration.isStyleEmpty(groupBySummaryStyle)) {
			configRegistry.registerConfigAttribute(
					CellConfigAttributes.CELL_STYLE,
					groupBySummaryStyle,
					DisplayMode.SELECT,
					GroupByDataLayer.GROUP_BY_SUMMARY);
		}
		
		ICellPainter cellPainter = getGroupBySummarySelectionCellPainter();
		if (cellPainter != null) {
			configRegistry.registerConfigAttribute(
					CellConfigAttributes.CELL_PAINTER, 
					cellPainter, 
					DisplayMode.SELECT, 
					GroupByDataLayer.GROUP_BY_SUMMARY);
		}
	}
	
	/**
	 * Returns the {@link IStyle} that should be used to render the selected GroupBy row summary cells 
	 * in a NatTable.
	 * <p>
	 * That means this {@link IStyle} is registered against {@link DisplayMode#SELECT}
	 * and the configuration label {@link GroupByDataLayer#GROUP_BY_SUMMARY}.
	 * </p>
	 * <p>
	 * If this method returns <code>null</code>, no value will be registered to keep the
	 * IConfigRegistry clean. The result would be the same, as if no value is found in the
	 * IConfigRegistry. In this case the rendering will fallback to the default configuration.
	 * </p>
	 * @return The {@link IStyle} that should be used to render the selected GroupBy summary row cells 
	 * 			in a NatTable. 
	 */
	protected IStyle getGroupBySummarySelectionStyle() {
		IStyle cellStyle = new Style();
		cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, groupBySummarySelectionBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, groupBySummarySelectionFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_BACKGROUND_COLOR, groupBySummarySelectionGradientBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_FOREGROUND_COLOR, groupBySummarySelectionGradientFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, groupBySummarySelectionHAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, groupBySummarySelectionVAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.FONT, groupBySummarySelectionFont);
		cellStyle.setAttributeValue(CellStyleAttributes.IMAGE, groupBySummarySelectionImage);
		cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, groupBySummarySelectionBorderStyle);
		cellStyle.setAttributeValue(CellStyleAttributes.PASSWORD_ECHO_CHAR, groupBySummarySelectionPWEchoChar);
		cellStyle.setAttributeValue(CellStyleAttributes.TEXT_DECORATION, groupBySummarySelectionTextDecoration);
		return cellStyle;
	}
	
	/**
	 * Returns the {@link ICellPainter} that should be used to render the selected GroupBy row summary cells 
	 * in a NatTable. Typically you should use a {@link GroupByCellTextPainter} is most inner cell painter, 
	 * as it is configured to only render group by content like group by summary and tree cells.
	 * <p>
	 * That means this {@link ICellPainter} is registered against {@link DisplayMode#SELECT}
	 * and the configuration label {@link GroupByDataLayer#GROUP_BY_SUMMARY}.
	 * </p>
	 * <p>
	 * If this method returns <code>null</code>, no value will be registered to keep the
	 * IConfigRegistry clean. The result would be the same, as if no value is found in the
	 * IConfigRegistry. In this case the rendering will fallback to the default configuration.
	 * </p>
	 * @return The {@link ICellPainter} that should be used to render the selected GroupBy row summary cells 
	 * 			in a NatTable.
	 */
	protected ICellPainter getGroupBySummarySelectionCellPainter() {
		return this.groupBySummarySelectionCellPainter;
	}

	/**
	 * Method to configure the styling of the GroupBy hint in the GroupBy header.
	 * <p>
	 * The GroupBy hint is usually rendered by the GroupByHeaderPainter. If you registered
	 * a different {@link ICellPainter} for the GroupByHeaderLayer, this might not be interpreted.
	 * </p>
	 * @param configRegistry The IConfigRegistry that is used by the NatTable instance
	 * 			to which the style configuration should be applied to.
	 */
	protected void configureGroupByHint(IConfigRegistry configRegistry) {
		String groupByHint = getGroupByHint();
		if (groupByHint != null && groupByHint.length() > 0) {
			configRegistry.registerConfigAttribute(
					GroupByConfigAttributes.GROUP_BY_HINT,
					groupByHint);
		}
		
		IStyle hintStyle = getGroupByHintStyle();
		if (!ThemeConfiguration.isStyleEmpty(hintStyle)) {
			configRegistry.registerConfigAttribute(
					GroupByConfigAttributes.GROUP_BY_HINT_STYLE,
					hintStyle);
		}
	}
	
	/**
	 * @return The hint that should be rendered in case there is no grouping applied.
	 */
	protected String getGroupByHint() {
		return this.groupByHint;
	}
	
	/**
	 * @return The {@link IStyle} that should be used to render the GroupBy hint.
	 */
	protected IStyle getGroupByHintStyle() {
		IStyle cellStyle = new Style();
		cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, groupByHintBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, groupByHintFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_BACKGROUND_COLOR, groupByHintGradientBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_FOREGROUND_COLOR, groupByHintGradientFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, groupByHintHAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, groupByHintVAlign);
		cellStyle.setAttributeValue(CellStyleAttributes.FONT, groupByHintFont);
		cellStyle.setAttributeValue(CellStyleAttributes.IMAGE, groupByHintImage);
		cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, groupByHintBorderStyle);
		cellStyle.setAttributeValue(CellStyleAttributes.PASSWORD_ECHO_CHAR, groupByHintPWEchoChar);
		cellStyle.setAttributeValue(CellStyleAttributes.TEXT_DECORATION, groupByHintTextDecoration);
		return cellStyle;
	}
	
	@Override
	public void unregisterStyles(IConfigRegistry configRegistry) {
		if (!ThemeConfiguration.isStyleEmpty(getGroupByStyle()))
			configRegistry.unregisterConfigAttribute(
					CellConfigAttributes.CELL_STYLE,
					DisplayMode.NORMAL,
					GroupByHeaderLayer.GROUP_BY_REGION);
		
		String groupByHint = getGroupByHint();
		if (groupByHint != null && groupByHint.length() > 0)
			configRegistry.unregisterConfigAttribute(
					GroupByConfigAttributes.GROUP_BY_HINT,
					groupByHint);

		if (!ThemeConfiguration.isStyleEmpty(getGroupByObjectStyle()))
			configRegistry.unregisterConfigAttribute(
					CellConfigAttributes.CELL_STYLE,
					DisplayMode.NORMAL,
					GroupByDataLayer.GROUP_BY_OBJECT);
		if (getGroupByObjectCellPainter() != null)
			configRegistry.unregisterConfigAttribute(
					CellConfigAttributes.CELL_PAINTER, 
					DisplayMode.NORMAL, 
					GroupByDataLayer.GROUP_BY_OBJECT);

		if (!ThemeConfiguration.isStyleEmpty(getGroupBySummaryStyle()))
			configRegistry.unregisterConfigAttribute(
					CellConfigAttributes.CELL_STYLE,
					DisplayMode.NORMAL,
					GroupByDataLayer.GROUP_BY_SUMMARY);
		if (getGroupBySummaryCellPainter() != null)
			configRegistry.unregisterConfigAttribute(
					CellConfigAttributes.CELL_PAINTER, 
					DisplayMode.NORMAL, 
					GroupByDataLayer.GROUP_BY_SUMMARY);
		
		if (!ThemeConfiguration.isStyleEmpty(getGroupByHintStyle()))
			configRegistry.unregisterConfigAttribute(
					GroupByConfigAttributes.GROUP_BY_HINT_STYLE);
	}
}
