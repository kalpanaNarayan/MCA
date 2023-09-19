from lxml import etree

xml_tree = etree.parse("xml/jokes.xml") # type: ignore

xsl_transform = etree.XSLT(etree.parse("xml/jokes.xsl")) # type: ignore

html_tree = xsl_transform(xml_tree)

with open("kalpana.html", "wb") as output_file:
    output_file.write(etree.tostring(html_tree, pretty_print=True)) # type: ignore