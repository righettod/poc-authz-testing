//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema.
//


package eu.righettod.pocauthztesting.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;


/**
 * The schema <b>authorization-matrix.xsd</b> specifies the expected content contained within this class.
 * <br>
 * Class generated with the XJC command: <code>xjc -d generated authorization-matrix.xsd</code>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "roles",
        "services",
        "servicesTesting"
})
@XmlRootElement(name = "authorization-matrix")
public class AuthorizationMatrix {

    @XmlElement(required = true)
    protected AuthorizationMatrix.Roles roles;
    @XmlElement(required = true)
    protected AuthorizationMatrix.Services services;
    @XmlElement(name = "services-testing", required = true)
    protected AuthorizationMatrix.ServicesTesting servicesTesting;

    /**
     * Gets the value of the roles property.
     *
     * @return possible object is
     * {@link AuthorizationMatrix.Roles }
     */
    public AuthorizationMatrix.Roles getRoles() {
        return roles;
    }

    /**
     * Sets the value of the roles property.
     *
     * @param value allowed object is
     *              {@link AuthorizationMatrix.Roles }
     */
    public void setRoles(AuthorizationMatrix.Roles value) {
        this.roles = value;
    }

    /**
     * Gets the value of the services property.
     *
     * @return possible object is
     * {@link AuthorizationMatrix.Services }
     */
    public AuthorizationMatrix.Services getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     *
     * @param value allowed object is
     *              {@link AuthorizationMatrix.Services }
     */
    public void setServices(AuthorizationMatrix.Services value) {
        this.services = value;
    }

    /**
     * Gets the value of the servicesTesting property.
     *
     * @return possible object is
     * {@link AuthorizationMatrix.ServicesTesting }
     */
    public AuthorizationMatrix.ServicesTesting getServicesTesting() {
        return servicesTesting;
    }

    /**
     * Sets the value of the servicesTesting property.
     *
     * @param value allowed object is
     *              {@link AuthorizationMatrix.ServicesTesting }
     */
    public void setServicesTesting(AuthorizationMatrix.ServicesTesting value) {
        this.servicesTesting = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="role" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="name">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;enumeration value="ANONYMOUS"/>
     *                       &lt;enumeration value="ADMIN"/>
     *                       &lt;enumeration value="BASIC"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "role"
    })
    public static class Roles {

        @XmlElement(required = true)
        protected List<AuthorizationMatrix.Roles.Role> role;

        /**
         * Gets the value of the role property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the role property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRole().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AuthorizationMatrix.Roles.Role }
         */
        public List<AuthorizationMatrix.Roles.Role> getRole() {
            if (role == null) {
                role = new ArrayList<AuthorizationMatrix.Roles.Role>();
            }
            return this.role;
        }


        /**
         * <p>Java class for anonymous complex type.
         * <p>
         * <p>The following schema fragment specifies the expected content contained within this class.
         * <p>
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="name">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;enumeration value="ANONYMOUS"/>
         *             &lt;enumeration value="ADMIN"/>
         *             &lt;enumeration value="BASIC"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Role {

            @XmlAttribute(name = "name")
            protected String name;
            @XmlAttribute(name = "description")
            protected String description;

            /**
             * Gets the value of the name property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the description property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getDescription() {
                return description;
            }

            /**
             * Sets the value of the description property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setDescription(String value) {
                this.description = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="service" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="role" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="name">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                 &lt;enumeration value="ANONYMOUS"/>
     *                                 &lt;enumeration value="ADMIN"/>
     *                                 &lt;enumeration value="BASIC"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="http-method" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="http-response-code-for-access-allowed" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                 &lt;attribute name="http-response-code-for-access-denied" type="{http://www.w3.org/2001/XMLSchema}int" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "service"
    })
    public static class Services {

        @XmlElement(required = true)
        protected List<AuthorizationMatrix.Services.Service> service;

        /**
         * Gets the value of the service property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the service property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getService().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AuthorizationMatrix.Services.Service }
         */
        public List<AuthorizationMatrix.Services.Service> getService() {
            if (service == null) {
                service = new ArrayList<AuthorizationMatrix.Services.Service>();
            }
            return this.service;
        }


        /**
         * <p>Java class for anonymous complex type.
         * <p>
         * <p>The following schema fragment specifies the expected content contained within this class.
         * <p>
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="role" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="name">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                       &lt;enumeration value="ANONYMOUS"/>
         *                       &lt;enumeration value="ADMIN"/>
         *                       &lt;enumeration value="BASIC"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="http-method" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="http-response-code-for-access-allowed" type="{http://www.w3.org/2001/XMLSchema}int" />
         *       &lt;attribute name="http-response-code-for-access-denied" type="{http://www.w3.org/2001/XMLSchema}int" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "role"
        })
        public static class Service {

            @XmlElement(required = true)
            protected List<AuthorizationMatrix.Services.Service.Role> role;
            @XmlAttribute(name = "name")
            protected String name;
            @XmlAttribute(name = "uri")
            protected String uri;
            @XmlAttribute(name = "http-method")
            protected String httpMethod;
            @XmlAttribute(name = "http-response-code-for-access-allowed")
            protected Integer httpResponseCodeForAccessAllowed;
            @XmlAttribute(name = "http-response-code-for-access-denied")
            protected Integer httpResponseCodeForAccessDenied;

            /**
             * Gets the value of the role property.
             * <p>
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the role property.
             * <p>
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getRole().add(newItem);
             * </pre>
             * <p>
             * <p>
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link AuthorizationMatrix.Services.Service.Role }
             */
            public List<AuthorizationMatrix.Services.Service.Role> getRole() {
                if (role == null) {
                    role = new ArrayList<AuthorizationMatrix.Services.Service.Role>();
                }
                return this.role;
            }

            /**
             * Gets the value of the name property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the uri property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getUri() {
                return uri;
            }

            /**
             * Sets the value of the uri property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setUri(String value) {
                this.uri = value;
            }

            /**
             * Gets the value of the httpMethod property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getHttpMethod() {
                return httpMethod;
            }

            /**
             * Sets the value of the httpMethod property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setHttpMethod(String value) {
                this.httpMethod = value;
            }

            /**
             * Gets the value of the httpResponseCodeForAccessAllowed property.
             *
             * @return possible object is
             * {@link Integer }
             */
            public Integer getHttpResponseCodeForAccessAllowed() {
                return httpResponseCodeForAccessAllowed;
            }

            /**
             * Sets the value of the httpResponseCodeForAccessAllowed property.
             *
             * @param value allowed object is
             *              {@link Integer }
             */
            public void setHttpResponseCodeForAccessAllowed(Integer value) {
                this.httpResponseCodeForAccessAllowed = value;
            }

            /**
             * Gets the value of the httpResponseCodeForAccessDenied property.
             *
             * @return possible object is
             * {@link Integer }
             */
            public Integer getHttpResponseCodeForAccessDenied() {
                return httpResponseCodeForAccessDenied;
            }

            /**
             * Sets the value of the httpResponseCodeForAccessDenied property.
             *
             * @param value allowed object is
             *              {@link Integer }
             */
            public void setHttpResponseCodeForAccessDenied(Integer value) {
                this.httpResponseCodeForAccessDenied = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * <p>
             * <p>The following schema fragment specifies the expected content contained within this class.
             * <p>
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="name">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *             &lt;enumeration value="ANONYMOUS"/>
             *             &lt;enumeration value="ADMIN"/>
             *             &lt;enumeration value="BASIC"/>
             *           &lt;/restriction>
             *         &lt;/simpleType>
             *       &lt;/attribute>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Role {

                @XmlAttribute(name = "name")
                protected String name;

                /**
                 * Gets the value of the name property.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Sets the value of the name property.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * <p>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="service" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="payload">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="content-type" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="name">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;enumeration value="ANONYMOUS"/>
     *                       &lt;enumeration value="ADMIN"/>
     *                       &lt;enumeration value="BASIC"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "service"
    })
    public static class ServicesTesting {

        @XmlElement(required = true)
        protected List<AuthorizationMatrix.ServicesTesting.Service> service;

        /**
         * Gets the value of the service property.
         * <p>
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the service property.
         * <p>
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getService().add(newItem);
         * </pre>
         * <p>
         * <p>
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AuthorizationMatrix.ServicesTesting.Service }
         */
        public List<AuthorizationMatrix.ServicesTesting.Service> getService() {
            if (service == null) {
                service = new ArrayList<AuthorizationMatrix.ServicesTesting.Service>();
            }
            return this.service;
        }


        /**
         * <p>Java class for anonymous complex type.
         * <p>
         * <p>The following schema fragment specifies the expected content contained within this class.
         * <p>
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="payload">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="content-type" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="name">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;enumeration value="ANONYMOUS"/>
         *             &lt;enumeration value="ADMIN"/>
         *             &lt;enumeration value="BASIC"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "payload"
        })
        public static class Service {

            @XmlElement(required = true)
            protected AuthorizationMatrix.ServicesTesting.Service.Payload payload;
            @XmlAttribute(name = "name")
            protected String name;

            /**
             * Gets the value of the payload property.
             *
             * @return possible object is
             * {@link AuthorizationMatrix.ServicesTesting.Service.Payload }
             */
            public AuthorizationMatrix.ServicesTesting.Service.Payload getPayload() {
                return payload;
            }

            /**
             * Sets the value of the payload property.
             *
             * @param value allowed object is
             *              {@link AuthorizationMatrix.ServicesTesting.Service.Payload }
             */
            public void setPayload(AuthorizationMatrix.ServicesTesting.Service.Payload value) {
                this.payload = value;
            }

            /**
             * Gets the value of the name property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setName(String value) {
                this.name = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * <p>
             * <p>The following schema fragment specifies the expected content contained within this class.
             * <p>
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *       &lt;attribute name="content-type" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "value"
            })
            public static class Payload {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "content-type")
                protected String contentType;

                /**
                 * Gets the value of the value property.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the contentType property.
                 *
                 * @return possible object is
                 * {@link String }
                 */
                public String getContentType() {
                    return contentType;
                }

                /**
                 * Sets the value of the contentType property.
                 *
                 * @param value allowed object is
                 *              {@link String }
                 */
                public void setContentType(String value) {
                    this.contentType = value;
                }

            }

        }

    }

}
