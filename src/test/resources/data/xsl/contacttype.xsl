<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:jdf="http://www.CIP4.org/JDFSchema_1_1">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" />
	<xsl:strip-space elements="*" />

	<!-- identity transform -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>

	<!-- fix @ContactTypes -->
	<xsl:template match="//jdf:Contact/@ContactTypes">
		<xsl:attribute name="ContactTypes">
			<xsl:value-of select="concat(.,' Approver')" />
		</xsl:attribute>
	</xsl:template>

</xsl:stylesheet>
