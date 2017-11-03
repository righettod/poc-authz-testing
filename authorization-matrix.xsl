<?xml version="1.0" encoding="UTF-8"?>
<!-- Convert the Authorization Matrix to an HTML rendering facilitating the review, audit and discution about the authorization matrix in order to spot inconsistency -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/">
    <html>
      <head>
        <title>Authorization Matrix</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous" />
      </head>
      <body>
        <h3>Roles</h3>
        <ul>
          <xsl:for-each select="authorization-matrix/roles/role">
            <xsl:choose>
              <xsl:when test="@name = 'ADMIN'">
                <div class="alert alert-warning" role="alert">
                  <strong>
                    <xsl:value-of select="@name" />
                  </strong>
                  :
                  <xsl:value-of select="@description" />
                </div>
              </xsl:when>
              <xsl:when test="@name = 'BASIC'">
                <div class="alert alert-info" role="alert">
                  <strong>
                    <xsl:value-of select="@name" />
                  </strong>
                  :
                  <xsl:value-of select="@description" />
                </div>
              </xsl:when>
              <xsl:otherwise>
                <div class="alert alert-danger" role="alert">
                  <strong>
                    <xsl:value-of select="@name" />
                  </strong>
                  :
                  <xsl:value-of select="@description" />
                </div>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:for-each>
        </ul>
        <h3>Authorizations</h3>
        <table class="table table-hover table-sm">
          <thead class="thead-inverse">
            <tr>
              <th>Service</th>
              <th>URI</th>
              <th>Method</th>
              <th>Role</th>
            </tr>
          </thead>
          <tbody>
            <xsl:for-each select="authorization-matrix/services/service">
              <xsl:variable name="service-name" select="@name" />
              <xsl:variable name="service-uri" select="@uri" />
              <xsl:variable name="service-method" select="@http-method" />
              <xsl:for-each select="role">
                <tr>
                  <td scope="row">
                    <xsl:value-of select="$service-name" />
                  </td>
                  <td>
                    <xsl:value-of select="$service-uri" />
                  </td>
                  <td>
                    <xsl:value-of select="$service-method" />
                  </td>
                  <td>
                    <xsl:variable name="service-role-name" select="@name" />
                    <xsl:choose>
                      <xsl:when test="@name = 'ADMIN'">
                        <div class="alert alert-warning" role="alert">
                          <xsl:value-of select="@name" />
                        </div>
                      </xsl:when>
                      <xsl:when test="@name = 'BASIC'">
                        <div class="alert alert-info" role="alert">
                          <xsl:value-of select="@name" />
                        </div>
                      </xsl:when>
                      <xsl:otherwise>
                        <div class="alert alert-danger" role="alert">
                          <xsl:value-of select="@name" />
                        </div>
                      </xsl:otherwise>
                    </xsl:choose>
                  </td>
                </tr>
              </xsl:for-each>
            </xsl:for-each>
          </tbody>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
