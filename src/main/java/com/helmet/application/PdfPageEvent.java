package com.helmet.application;

import java.util.StringTokenizer;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfPageEvent extends PdfPageEventHelper
{
  private String freeText;
  private Font freeTextFont;

  /**
   * @return the freeText
   */
  public String getFreeText()
  {
    return freeText;
  }

  /**
   * @param freeText the freeText to set
   */
  public void setFreeText(String freeText)
  {
    this.freeText = freeText;
  }

  /**
   * @return the freeTextFont
   */
  public Font getFreeTextFont()
  {
    return freeTextFont;
  }

  /**
   * @param freeTextFont the freeTextFont to set
   */
  public void setFreeTextFont(Font freeTextFont)
  {
    this.freeTextFont = freeTextFont;
  }

  /* (non-Javadoc)
   * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
   */
  @Override
  public void onEndPage(PdfWriter pdfWriter, Document document)
  {
    super.onEndPage(pdfWriter, document);
    Rectangle rect = pdfWriter.getBoxSize("art");
    if (freeText != null)
    {
      StringTokenizer st = new StringTokenizer(freeText, "\n");
      String lineText = null;
      int lineNumber = 0;
      while (st.hasMoreTokens())
      {
        lineText = st.nextToken();
        ColumnText.showTextAligned(pdfWriter.getDirectContent(), Element.ALIGN_LEFT, new Paragraph(lineText, freeTextFont), (rect.getLeft()), rect.getBottom()
            - ((freeTextFont.getSize() + 5) * lineNumber++), 0);
      }
    }    
  }

}
