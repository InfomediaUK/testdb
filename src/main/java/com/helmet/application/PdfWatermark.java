package com.helmet.application;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfWatermark extends PdfPageEventHelper
{
  protected Phrase watermark = null;

  public PdfWatermark()
  {
    super();
    this.watermark = new Phrase("Waremark Text", new Font(FontFamily.HELVETICA, 60, Font.NORMAL, BaseColor.LIGHT_GRAY));
  }

  public PdfWatermark(String watermarkText)
  {
    super();
    this.watermark = new Phrase(watermarkText, new Font(FontFamily.HELVETICA, 60, Font.NORMAL, BaseColor.LIGHT_GRAY));
  }

  @Override
  public void onEndPage(PdfWriter writer, Document document) {
      PdfContentByte canvas = writer.getDirectContentUnder();
      ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 298, 421, 45);
  }

}
