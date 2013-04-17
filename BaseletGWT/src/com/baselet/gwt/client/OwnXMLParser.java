package com.baselet.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.baselet.element.GridElement;
import com.baselet.gwt.client.element.ElementFactory;
import com.baselet.gwt.client.view.DrawPanelCanvas;
import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.umlet.element.experimental.ElementId;

public class OwnXMLParser {

	private static final String NEWLINE_XML_ENCODED = "&#10;";
	
	private static final String ELEMENT = "element";
	private static final String ZOOM_LEVEL = "zoom_level";
	private static final String ID = "id";
	private static final String COORDINATES = "coordinates";
	private static final String X = "x";
	private static final String Y = "y";
	private static final String W = "w";
	private static final String H = "h";
	private static final String PANEL_ATTRIBUTES = "panel_attributes";
	private static final String ADDITIONAL_ATTRIBUTES = "additional_attributes";

	public static void parseAndInsertDiagram(String xml, DrawPanelCanvas handler) {
		List<GridElement> returnList = new ArrayList<GridElement>();
		  try {
			    // parse the XML document into a DOM
			    Document messageDom = XMLParser.parse(xml);

			    NodeList elements = messageDom.getElementsByTagName(ELEMENT);
			    for (int i = 0; i < elements.getLength(); i++) {
					Element element = (Element) elements.item(i);
					ElementId id = ElementId.valueOf(element.getElementsByTagName(ID).item(0).getFirstChild().getNodeValue());
					Element coordinates = (Element) element.getElementsByTagName(COORDINATES).item(0);
					int x = Integer.valueOf(coordinates.getElementsByTagName(X).item(0).getFirstChild().getNodeValue());
					int y = Integer.valueOf(coordinates.getElementsByTagName(Y).item(0).getFirstChild().getNodeValue());
					int w = Integer.valueOf(coordinates.getElementsByTagName(W).item(0).getFirstChild().getNodeValue());
					int h = Integer.valueOf(coordinates.getElementsByTagName(H).item(0).getFirstChild().getNodeValue());
					String panelAttributes = element.getElementsByTagName(PANEL_ATTRIBUTES).item(0).getFirstChild().getNodeValue();
					panelAttributes = panelAttributes.replace(NEWLINE_XML_ENCODED, "\n");
					
					String additionalPanelAttributes = "";
					Node additionalAttrNode = element.getElementsByTagName(ADDITIONAL_ATTRIBUTES).item(0);
					if (additionalAttrNode != null && additionalAttrNode.getFirstChild() != null) {
						additionalPanelAttributes = additionalAttrNode.getFirstChild().getNodeValue();
					}
					returnList.add(ElementFactory.create(id, new com.baselet.diagram.draw.geom.Rectangle(x, y, w, h), panelAttributes, additionalPanelAttributes, handler.getSelector()));
				}
			  } catch (DOMException e) {
			    Window.alert("Could not parse XML document.");
			  }
			handler.setGridElements(returnList);
	}

	public static String createXml(DrawPanelCanvas drawPanelCanvas) {
		Document doc = XMLParser.createDocument();

		Element zoomElement = doc.createElement(ZOOM_LEVEL);
		zoomElement.appendChild(doc.createTextNode("10"));
		
		Element diagramElement = doc.createElement("diagram");
		diagramElement.setAttribute("program", Utils.getProgramnameLowerCase());
		diagramElement.setAttribute("version", Utils.getProgramVersion());
		diagramElement.appendChild(zoomElement);
		doc.appendChild(diagramElement);
		
		for (GridElement ge : drawPanelCanvas.getGridElements()) {
			Element x = doc.createElement(X);
			x.appendChild(doc.createTextNode(ge.getRectangle().getX()+""));
			Element y = doc.createElement(Y);
			y.appendChild(doc.createTextNode(ge.getRectangle().getY()+""));
			Element w = doc.createElement(W);
			w.appendChild(doc.createTextNode(ge.getRectangle().getWidth()+""));
			Element h = doc.createElement(H);
			h.appendChild(doc.createTextNode(ge.getRectangle().getHeight()+""));

			Element coordinates = doc.createElement(COORDINATES);
			coordinates.appendChild(x);
			coordinates.appendChild(y);
			coordinates.appendChild(w);
			coordinates.appendChild(h);
			
			Element id = doc.createElement(ID);
			id.appendChild(doc.createTextNode(ge.getId().toString()));

			Element panelAttributes = doc.createElement(PANEL_ATTRIBUTES);
			String panelText = ge.getPanelAttributes().replace("\n", NEWLINE_XML_ENCODED);
			panelAttributes.appendChild(doc.createTextNode(panelText));

			Element additionalPanelAttributes = doc.createElement(ADDITIONAL_ATTRIBUTES);
			additionalPanelAttributes.appendChild(doc.createTextNode(ge.getAdditionalAttributes()));
			
			Element element = doc.createElement(ELEMENT);
			element.appendChild(id);
			element.appendChild(coordinates);
			element.appendChild(panelAttributes);
			element.appendChild(additionalPanelAttributes);
			
			diagramElement.appendChild(element);
		}
		return doc.toString();
	}
	
//	<?xml version="1.0" encoding="UTF-8" standalone="no"?>
//	<diagram program="umlet" version="12.1">
//	  <zoom_level>10</zoom_level>
//	  <element>
//	    <id>UMLClass</id>
//	    <coordinates>
//	      <x>240</x>
//	      <y>180</y>
//	      <w>180</w>
//	      <h>90</h>
//	    </coordinates>
//	    <panel_attributes>This palette contains the new grid elements UMLet offers since v12 (at the moment Class and UseCase).
//
//	Press Ctrl+Space to open the possible settings to customize your elements as you can see below.
//	elementstyle=wordwrap</panel_attributes>
//	    <additional_attributes/>
//	  </element>
}