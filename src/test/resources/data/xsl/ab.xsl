<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xjdf="http://www.CIP4.org/JDFSchema_2_0" xmlns="http://www.CIP4.org/JDFSchema_1_1">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:preserve-space elements="*"/>
	<xsl:template match="/a">
		<xsl:element name="a1">
			<xsl:attribute name="c1">
				<xsl:value-of select="b/@c"/>
			</xsl:attribute>
			<xsl:element name="b1">
				<xsl:attribute name="e1">
					<xsl:value-of select="b/@e"/>
				</xsl:attribute>
			</xsl:element>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
	