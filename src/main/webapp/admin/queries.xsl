<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
 <xsl:output method="html" indent="yes" />

 <xsl:param name="submitURL"/>
 
 <xsl:template match="queries">
 <xsl:apply-templates/>
 </xsl:template>
 
 <xsl:template match="query">
 <form action="{$submitURL}" method="post">
 <input type="hidden" name="id" value="{@id}"/>
 <xsl:value-of select="@desc"/>
 <br/>
 <xsl:apply-templates select="params"/>
 <input type="submit" value="go"/>
 </form>
 </xsl:template>

 <xsl:template match="params">
 <table><xsl:apply-templates/></table>
 </xsl:template>
 <xsl:template match="param">
 <tr>
   <td><xsl:value-of select="@title"/></td>
   <td><input type="text" name="{@name}"/></td>
 </tr>
 </xsl:template>
 
</xsl:stylesheet>

