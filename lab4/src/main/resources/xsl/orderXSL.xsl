<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" omit-xml-declaration="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Orders</title>
            </head>
            <body>
                <a href="/xsl/products">Show products</a>
            </body>
        </html>
        <table border="1" style="margin-top: 5px">
            <tr bgcolor="#CCCCCC">
                <td>
                    <strong>Id</strong>
                </td>
                <td>
                    <strong>Date</strong>
                </td>
                <td>
                    <strong>Products</strong>
                </td>
            </tr>
            <xsl:for-each select="List/item">
                <tr>
                    <td>
                        <xsl:value-of select="id"/>
                    </td>
                    <td>
                        <xsl:value-of select="date"/>
                    </td>
                    <td>
                        <xsl:apply-templates select="lineItems"/>
                    </td>
                </tr>
            </xsl:for-each>
        </table>

    </xsl:template>
    <xsl:template match="lineItems">
        <table>
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <xsl:for-each select="lineItems">
                <tr>
                    <td>
                        <xsl:apply-templates select="product" />
                    </td>
                    <td>
                        <xsl:value-of select="quantity" />
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
    <xsl:template match="product">
        <xsl:value-of select="serialNumber"/>
    </xsl:template>
</xsl:stylesheet>