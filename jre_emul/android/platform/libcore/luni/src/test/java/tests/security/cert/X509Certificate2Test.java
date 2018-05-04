/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.security.cert;

import org.apache.harmony.security.tests.support.cert.TestUtils;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.security.auth.x500.X500Principal;

public class X509Certificate2Test extends junit.framework.TestCase {

    /**
     * Test for X.509 Certificate provider
     */
    public void test_toString() throws Exception {

        // Regression for HARMONY-3384
        CertificateFactory certFact = CertificateFactory.getInstance("X509");
        X509Certificate pemCert = (X509Certificate) certFact
                .generateCertificate(new ByteArrayInputStream(TestUtils
                        .getX509Certificate_v3()));

        // extension value is empty sequence
        byte[] extnValue = pemCert.getExtensionValue("2.5.29.35");
        assertEquals(
                Arrays.toString(new byte[] { 0x04, 0x02, 0x30, 0x00 }),
                Arrays.toString(extnValue));
        assertNotNull(pemCert.toString());
        // End regression for HARMONY-3384
    }

    public void test_X509Certificate() {
        MyX509Certificate s = null;
        try {
            s = new MyX509Certificate();
        } catch (Exception e) {
            fail("Unexpected exception " + e.getMessage());
        }
        assertEquals("X.509", s.getType());
    }

    public void testAbstractMethods() {
        MyX509Certificate s = new MyX509Certificate();
        try {
            s.checkValidity();
            s.checkValidity(new Date());
            s.getBasicConstraints();
            s.getIssuerDN();
            s.getIssuerUniqueID();
            s.getKeyUsage();
            s.getNotAfter();
            s.getNotBefore();
            s.getSerialNumber();
            s.getSigAlgName();
            s.getSigAlgOID();
            s.getSigAlgParams();
            s.getSignature();
            s.getSubjectDN();
            s.getSubjectUniqueID();
            s.getTBSCertificate();
            s.getVersion();
        } catch (Exception e) {
            fail("Unexpected exception " + e.getMessage());
        }
    }

    // Base64 encoded form of ASN.1 DER encoded X.509 Certificate
    // (see RFC 3280 at http://www.ietf.org/rfc/rfc3280.txt)
    // (generated by using of classes from
    // org.apache.harmony.security.x509 package)
    private static String CERT =
        "MIIByzCCATagAwIBAgICAiswCwYJKoZIhvcNAQEFMB0xGzAZBgNVBAoT"
            + "EkNlcnRpZmljYXRlIElzc3VlcjAeFw0wNjA0MjYwNjI4MjJaFw0zMzAz"
            + "MDExNjQ0MDlaMB0xGzAZBgNVBAoTEkNlcnRpZmljYXRlIElzc3VlcjCB"
            + "nzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAkLGLsPdSPDMyP1OUOKu"
            + "U3cvbNK5RGaQ3bXc5aDjvApx43BcaoXgt6YD/5yXz0OsIooj5yA37bY"
            + "JGcVrvFD5FMPdDd3vjNPQOep0MzG4CdbkaZde5SigPabOMQYS4oUyLBx"
            + "W3LGG0mUODe5AGGqtqXU0GlKg4K2je6cCtookCUCAwEAAaMeMBwwGgYD"
            + "VR0RAQH/BBAwDoEMcmZjQDgyMi5OYW1lMAsGCSqGSIb3DQEBBQOBgQBZ"
            + "pVXj01dOpqnZErUQb50j8lJD1dIaz1eJTvJCSadj7ziV1VtnnapI07c"
            + "XEa7ONzcHQTYTG10poHfOK/a0BaULF3GlctDESilwQYbW5BdfpAlZpbH"
            + "AFLcUDh6Eq50kc0A/anh/j3mgBNuvbIMo7hHNnZB6k/prswm2BszyLD"
            + "yw==";
    private static String CERT_CORRECT =
        "-----BEGIN CERTIFICATE-----\n"
        + "MIIC+jCCAragAwIBAgICAiswDAYHKoZIzjgEAwEBADAdMRswGQYDVQQKExJDZXJ0a"
        + "WZpY2F0ZSBJc3N1ZXIwIhgPMTk3MDAxMTIxMzQ2NDBaGA8xOTcwMDEyNDAzMzMyMF"
        + "owHzEdMBsGA1UEChMUU3ViamVjdCBPcmdhbml6YXRpb24wGTAMBgcqhkjOOAQDAQE"
        + "AAwkAAQIDBAUGBwiBAgCqggIAVaOCAhQwggIQMA8GA1UdDwEB/wQFAwMBqoAwEgYD"
        + "VR0TAQH/BAgwBgEB/wIBBTAUBgNVHSABAf8ECjAIMAYGBFUdIAAwZwYDVR0RAQH/B"
        + "F0wW4EMcmZjQDgyMi5OYW1lggdkTlNOYW1lpBcxFTATBgNVBAoTDE9yZ2FuaXphdG"
        + "lvboYaaHR0cDovL3VuaWZvcm0uUmVzb3VyY2UuSWSHBP///wCIByoDolyDsgMwDAY"
        + "DVR0eAQH/BAIwADAMBgNVHSQBAf8EAjAAMIGZBgNVHSUBAf8EgY4wgYsGBFUdJQAG"
        + "CCsGAQUFBwMBBggrBgEFBQcDAQYIKwYBBQUHAwIGCCsGAQUFBwMDBggrBgEFBQcDB"
        + "AYIKwYBBQUHAwUGCCsGAQUFBwMGBggrBgEFBQcDBwYIKwYBBQUHAwgGCCsGAQUFBw"
        + "MJBggrBgEFBQgCAgYKKwYBBAGCNwoDAwYJYIZIAYb4QgQBMA0GA1UdNgEB/wQDAgE"
        + "BMA4GBCpNhgkBAf8EAwEBATBkBgNVHRIEXTBbgQxyZmNAODIyLk5hbWWCB2ROU05h"
        + "bWWkFzEVMBMGA1UEChMMT3JnYW5pemF0aW9uhhpodHRwOi8vdW5pZm9ybS5SZXNvd"
        + "XJjZS5JZIcE////AIgHKgOiXIOyAzAJBgNVHR8EAjAAMAoGA1UdIwQDAQEBMAoGA1"
        + "UdDgQDAQEBMAoGA1UdIQQDAQEBMAwGByqGSM44BAMBAQADMAAwLQIUAL4QvoazNWP"
        + "7jrj84/GZlhm09DsCFQCBKGKCGbrP64VtUt4JPmLjW1VxQA==\n"
        + "-----END CERTIFICATE-----";

    private static String CERT_TAMPERED = "-----BEGIN CERTIFICATE-----\n"
        + "MIIC+jCCAragAwIBAgICAiswDAYHKoZIzjgEAwEBADAdMRswGQYDVQQKExJDZXJ0a"
        + "WZpY2F0ZSBJc3N1ZXIwIhgPMTk3MDAxMTIxMzQ2NDBaGA8xOTcwMDEyNDAzMzMyMF"
        + "owHzEdMBsGA1UEChMUU3ViamVjdCBPcmdhbml6YXRpb24wGTAMBgcqhkjOOAQDAQE"
        + "AAwkAAQIDBAUGBwiBAgCqggIAVaOCAhQwggIQMA8GA1UdDwEB/wQFAwMBqoAwEgYD"
        + "VR0TAQH/BAgwBgEB/wIBBTAUBgNVHSABAf8ECjAIMAyGBFUdIAAwZwYDVR0RAQH/B"
        + "F0wW4EMcmZjQDgyMi5OYW1lggdkTlNOYW1lpBcxFTATBgNVBAoTDE9yZ2FuaXphdG"
        + "lvboYaaHR0cDovL3VuaWZvcm0uUmVzb3VyY2UuSWSHBP///wCIByoDolyDsgMwDAY"
        + "DVR0eAQH/BAIwADAMBgNVHSQBAf8EAjAAMIGZBgNVHSUBAf8EgY4wgYsGBFUdJQAG"
        + "CCsGAQUFBwMBBggrBgEFBQcDAQYIKxYBBQUHAwIGCCsGAQUFBwMDBggrBgEFBQcDB"
        + "AYIKwYBBQUHAwUGCCsGAQUFBwMGBggrBgEFBQcDBwYIKwYBBQUHAwgGCCsGAQUFBw"
        + "MJBggrBgEFBQgCAgYKKwYBBAGCNwoDAwYJYIZIAYb4QgQBMA0GA1UdNgEB/wQDAgE"
        + "BMA4GBCpNhgkBAf8EAwEBATBkBgNVHRIEXTBbgQxyZmNAODIyLk5hbWWCB2ROU05h"
        + "bWWkFzEVMBMGA1UEChMMT3JnYW5pemF0aW9uhhpodHRwOi8vdW5pZm9ybS5SZXNvd"
        + "XJjZS5JZIcE////AIgHKgOiXIOyAzAJBgNVHR8EAjAAMAoGA1UdIwQDAQEBMAoGA1"
        + "UdDgQDAQEBMAoGA1UdIQQDAQEBMAwHByqGSM44BAMBAQADMAAwLQIUAL4QvoazNWP"
        + "7jrj84/GZlhm09DsCFQCBKGKCGbrP64VtUt4JPmLjW1VxQA==\n"
        + "-----END CERTIFICATE-----";

    // Base64 encoded form of ASN.1 DER encoded X.509 CRL
    // (see RFC 3280 at http://www.ietf.org/rfc/rfc3280.txt)
    // (generated by using of classes from
    // org.apache.harmony.security.x509 package)
    private static String CRL =
        "MIHXMIGXAgEBMAkGByqGSM44BAMwFTETMBEGA1UEChMKQ1JMIElzc3Vl"
            + "chcNMDYwNDI3MDYxMzQ1WhcNMDYwNDI3MDYxNTI1WjBBMD8CAgIrFw0w"
            + "NjA0MjcwNjEzNDZaMCowCgYDVR0VBAMKAQEwHAYDVR0YBBUYEzIwMDYw"
            + "NDI3MDYxMzQ1LjQ2OFqgDzANMAsGA1UdFAQEBAQEBDAJBgcqhkjOOAQD"
            + "AzAAMC0CFQCk0t0DTyu82QpajbBlxX9uXvUDSgIUSBN4g+xTEeexs/0k"
            + "9AkjBhjF0Es=";

    // has stub implementation for abstract methods
    private static class MyX509Certificate extends X509Certificate implements
            X509Extension {

        private static final long serialVersionUID = -7196694072296607007L;

        public void checkValidity() {
        }

        public void checkValidity(Date date) {
        }

        public int getVersion() {
            return 3;
        }

        public BigInteger getSerialNumber() {
            return null;
        }

        public Principal getIssuerDN() {
            return null;
        }

        public Principal getSubjectDN() {
            return null;
        }

        public Date getNotBefore() {
            return null;
        }

        public Date getNotAfter() {
            return null;
        }

        public byte[] getTBSCertificate() {
            return null;
        }

        public byte[] getSignature() {
            return null;
        }

        public String getSigAlgName() {
            return null;
        }

        public String getSigAlgOID() {
            return null;
        }

        public byte[] getSigAlgParams() {
            return null;
        }

        public boolean[] getIssuerUniqueID() {
            return null;
        }

        public boolean[] getSubjectUniqueID() {
            return null;
        }

        public boolean[] getKeyUsage() {
            return null;
        }

        public int getBasicConstraints() {
            return 0;
        }

        public void verify(PublicKey key) {
        }

        public void verify(PublicKey key, String sigProvider) {
        }

        public String toString() {
            return "";
        }

        public PublicKey getPublicKey() {
            return null;
        }

        public byte[] getEncoded() {
            return null;
        }

        public Set<String> getNonCriticalExtensionOIDs() {
            return null;
        }

        public Set<String> getCriticalExtensionOIDs() {
            return null;
        }

        public byte[] getExtensionValue(String oid) {
            return null;
        }

        public boolean hasUnsupportedCriticalExtension() {
            return false;
        }
    }

    public void testGetType() {
        assertEquals("X.509", new MyX509Certificate().getType());
    }

    public void testGetIssuerX500Principal() {
        // return valid encoding
        MyX509Certificate cert = new MyX509Certificate() {
            private static final long serialVersionUID = 638659908323741165L;

            public byte[] getEncoded() {
                return TestUtils.getX509Certificate_v1();
            }
        };

        assertEquals(new X500Principal("CN=Z"), cert.getIssuerX500Principal());
    }

    public void testGetSubjectX500Principal() {
        // return valid encoding
        MyX509Certificate cert = new MyX509Certificate() {
            private static final long serialVersionUID = -3625913637413840694L;

            public byte[] getEncoded() {
                return TestUtils.getX509Certificate_v1();
            }
        };

        assertEquals(new X500Principal("CN=Y"), cert.getSubjectX500Principal());
    }

    public void testGetExtendedKeyUsage() throws Exception {
        assertNull(new MyX509Certificate().getExtendedKeyUsage());
        X509Certificate cert = generateCert(CERT_CORRECT);
        List<String> l = cert.getExtendedKeyUsage();
        assertNotNull(l);

        try {
            l.clear();
        } catch (UnsupportedOperationException expected) {
        }

        try {
            l.add("Test");
        } catch (UnsupportedOperationException expected) {
        }

        try {
            l.remove(0);
        } catch (UnsupportedOperationException expected) {
        }
    }

    private static final String CERT_WITHOUT_BASIC
        = ("-----BEGIN CERTIFICATE-----\n"
           + "MIIG9TCCBd2gAwIBAgIPLXR4AWpp9+O6Jn4rZpkgMA0GCSqGSIb3DQEBBQUAME0x\n"
           + "CzAJBgNVBAYTAkNIMRUwEwYDVQQKEwxTd2lzc1NpZ24gQUcxJzAlBgNVBAMTHlN3\n"
           + "aXNzU2lnbiBFViBHb2xkIENBIDIwMDkgLSBHMjAeFw0xMjA3MjYwODU4MTNaFw0x\n"
           + "NDA3MjYwODU4MTNaMIIBITELMAkGA1UEBhMCQ0gxEDAOBgNVBAgMB1rDvHJpY2gx\n"
           + "EzARBgNVBAcTCkdsYXR0YnJ1Z2cxFTATBgNVBAoTDFN3aXNzU2lnbiBBRzEWMBQG\n"
           + "A1UEAxMNc3dpc3NzaWduLmNvbTEnMCUGCSqGSIb3DQEJARYYb3BlcmF0aW9uc0Bz\n"
           + "d2lzc3NpZ24uY29tMRswGQYDVQQJDBJTw6RnZXJlaXN0cmFzc2UgMjUxDTALBgNV\n"
           + "BBETBDgxNTIxEzARBgsrBgEEAYI3PAIBAxMCQ0gxGDAWBgsrBgEEAYI3PAIBAgwH\n"
           + "WsO8cmljaDEbMBkGA1UEBRMSQ0gtMDIwLjMuMDI1LjExMC03MRswGQYDVQQPExJW\n"
           + "MS4wLCBDbGF1c2UgNS4oYikwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIB\n"
           + "AQDLjzHfEcDeIwdEatC73JRs/xRaDLDmzwwHSZCjCvIKe8/yXxLR3cUIBG8mKrql\n"
           + "1yICAMEpNM7J/fwN248OV6X/UosJpC4vmbpzgAN8y2q1DGnOyX7Eyi3UDXLTXtfA\n"
           + "4294BMqCym5zzdS932aQPYBayFkzcsQSp6DHRAuj2Xxd9bly/urNKTumO8ZE0RFR\n"
           + "wVgNU7o3OQepsH3bhe060Jlr6EBLFas0scH6ll8fREI8g+xhs8yHBOL/meE3zVQC\n"
           + "/3KTyhY82R4xJy38YHCFPrwrtz5ZHpJqQ1LjiG+cX+FReoHp5VoV7LBNj+eL8oZb\n"
           + "G6Zn5xlsBQgTlOxEIbXLVV13AgMBAAGjggL6MIIC9jBLBgNVHREERDBCgg1zd2lz\n"
           + "c3NpZ24uY29tghF3d3cuc3dpc3NzaWduLmNvbYIMc3dpc3NzaWduLmNoghB3d3cu\n"
           + "c3dpc3NzaWduLmNoMA4GA1UdDwEB/wQEAwIFoDAdBgNVHSUEFjAUBggrBgEFBQcD\n"
           + "AQYIKwYBBQUHAwIwHQYDVR0OBBYEFPDLqEiSWR5JiwPlqngv2n2WJwVRMB8GA1Ud\n"
           + "IwQYMBaAFIh0Rm3HfLX6cnEZ3r8nXg1o4PcnMIH/BgNVHR8EgfcwgfQwR6BFoEOG\n"
           + "QWh0dHA6Ly9jcmwuc3dpc3NzaWduLm5ldC84ODc0NDY2REM3N0NCNUZBNzI3MTE5\n"
           + "REVCRjI3NUUwRDY4RTBGNzI3MIGooIGloIGihoGfbGRhcDovL2RpcmVjdG9yeS5z\n"
           + "d2lzc3NpZ24ubmV0L0NOPTg4NzQ0NjZEQzc3Q0I1RkE3MjcxMTlERUJGMjc1RTBE\n"
           + "NjhFMEY3MjclMkNPPVN3aXNzU2lnbiUyQ0M9Q0g/Y2VydGlmaWNhdGVSZXZvY2F0\n"
           + "aW9uTGlzdD9iYXNlP29iamVjdENsYXNzPWNSTERpc3RyaWJ1dGlvblBvaW50MGIG\n"
           + "A1UdIARbMFkwVwYJYIV0AVkBAgEBMEowSAYIKwYBBQUHAgEWPGh0dHA6Ly9yZXBv\n"
           + "c2l0b3J5LnN3aXNzc2lnbi5jb20vU3dpc3NTaWduLUdvbGQtQ1AtQ1BTLVI1LnBk\n"
           + "ZjCB0QYIKwYBBQUHAQEEgcQwgcEwZAYIKwYBBQUHMAKGWGh0dHA6Ly9zd2lzc3Np\n"
           + "Z24ubmV0L2NnaS1iaW4vYXV0aG9yaXR5L2Rvd25sb2FkLzg4NzQ0NjZEQzc3Q0I1\n"
           + "RkE3MjcxMTlERUJGMjc1RTBENjhFMEY3MjcwWQYIKwYBBQUHMAGGTWh0dHA6Ly9n\n"
           + "b2xkLWV2LWcyLm9jc3Auc3dpc3NzaWduLm5ldC84ODc0NDY2REM3N0NCNUZBNzI3\n"
           + "MTE5REVCRjI3NUUwRDY4RTBGNzI3MA0GCSqGSIb3DQEBBQUAA4IBAQA8kdxUZdXa\n"
           + "qu1EATZM77OhA4jw4rmrVNA+iQDb1NdlPldbc5PyQoIWdn7dJgzZrmupgOurRsol\n"
           + "kUoXb2GrZDaiSK+2sW7VQAcS3p4yK1MawGpcekVcOiFkCjFvuqkwdgnOeZpFIJzP\n"
           + "Nh6W0wkAxbAVwP/cAOFSoCKTdTfxLMU2g8g+7J49BagYm/b3h1UmvL+B4s7XzL+D\n"
           + "QDiKzIUvb4xwmbDYksgflkOBwliG3sC8H6LDD+2n3ukFOOKyiXQnoz2QJ57R/Jhj\n"
           + "kgKyXcr7+6RxatGM7K1u7RlfhuxQxvvrb0NTS8ojLwx6fZL1qYqRGjDWhTv36aRu\n"
           + "nbZMIuE5QJQs\n"
           + "-----END CERTIFICATE-----\n");

    private static final String CERT_WITH_BASIC_NON_CA
        = ("-----BEGIN CERTIFICATE-----\n"
           + "MIIGwDCCBaigAwIBAgIQBXBpbXU7lyKUBaP2n+mqwjANBgkqhkiG9w0BAQUFADCB\n"
           + "vjELMAkGA1UEBhMCVVMxFzAVBgNVBAoTDlZlcmlTaWduLCBJbmMuMR8wHQYDVQQL\n"
           + "ExZWZXJpU2lnbiBUcnVzdCBOZXR3b3JrMTswOQYDVQQLEzJUZXJtcyBvZiB1c2Ug\n"
           + "YXQgaHR0cHM6Ly93d3cudmVyaXNpZ24uY29tL3JwYSAoYykwNjE4MDYGA1UEAxMv\n"
           + "VmVyaVNpZ24gQ2xhc3MgMyBFeHRlbmRlZCBWYWxpZGF0aW9uIFNTTCBTR0MgQ0Ew\n"
           + "HhcNMTIxMDEwMDAwMDAwWhcNMTQxMDEwMjM1OTU5WjCCASUxEzARBgsrBgEEAYI3\n"
           + "PAIBAxMCVVMxGTAXBgsrBgEEAYI3PAIBAhMIRGVsYXdhcmUxHTAbBgNVBA8TFFBy\n"
           + "aXZhdGUgT3JnYW5pemF0aW9uMRAwDgYDVQQFEwcyMTU4MTEzMQswCQYDVQQGEwJV\n"
           + "UzEOMAwGA1UEERQFOTQwNDMxEzARBgNVBAgTCkNhbGlmb3JuaWExFjAUBgNVBAcU\n"
           + "DU1vdW50YWluIFZpZXcxGTAXBgNVBAkUEDM1MCBFbGxpcyBTdHJlZXQxHTAbBgNV\n"
           + "BAoUFFN5bWFudGVjIENvcnBvcmF0aW9uMSMwIQYDVQQLFBpJbmZyYXN0cnVjdHVy\n"
           + "ZSAgT3BlcmF0aW9uczEZMBcGA1UEAxQQd3d3LnZlcmlzaWduLmNvbTCCASIwDQYJ\n"
           + "KoZIhvcNAQEBBQADggEPADCCAQoCggEBAM0oFzrY8FaYnXJSzme9WCwB3wPB+HS8\n"
           + "blBuW6DbI11w7In0P6BCVwt/WqI1a+VwSfliKv7pD2P6eHvu6eb8ipPGF3xBRmtr\n"
           + "Ttg9am77taHkB+w1trx9xXio0viFOPYf9mt2yNhCatjKeXnPRH8IoLoI5bqBhv8V\n"
           + "u/Mg9s1Wwe8mW1zxztD3D0fVkWqMpQRLFLrs3Us58SbnaxbFLEmAQHPgrDwi+IC4\n"
           + "aQWcf4UbCkA5P0at+svsu/G+KwYBrsVFL6NaoATcyqimckyCVxeKK6QEPRPM34ae\n"
           + "7HpT9OWmCu+r4GhM7AQS2mY3wF1EhtigXyUUteU/H06kWyVybpy2VwcCAwEAAaOC\n"
           + "Ak4wggJKMIHEBgNVHREEgbwwgbmCEHd3dy52ZXJpc2lnbi5jb22CDHZlcmlzaWdu\n"
           + "LmNvbYIQd3d3LnZlcmlzaWduLm5ldIIMdmVyaXNpZ24ubmV0ghF3d3cudmVyaXNp\n"
           + "Z24ubW9iaYINdmVyaXNpZ24ubW9iaYIPd3d3LnZlcmlzaWduLmV1ggt2ZXJpc2ln\n"
           + "bi5ldYIVZm9ybXMud3Muc3ltYW50ZWMuY29tgg1zc2xyZXZpZXcuY29tghF3d3cu\n"
           + "c3NscmV2aWV3LmNvbTAJBgNVHRMEAjAAMB0GA1UdDgQWBBSFo5HyhWbCi1NFKniM\n"
           + "6xYHuroUUDAOBgNVHQ8BAf8EBAMCBaAwPgYDVR0fBDcwNTAzoDGgL4YtaHR0cDov\n"
           + "L0VWSW50bC1jcmwudmVyaXNpZ24uY29tL0VWSW50bDIwMDYuY3JsMEQGA1UdIAQ9\n"
           + "MDswOQYLYIZIAYb4RQEHFwYwKjAoBggrBgEFBQcCARYcaHR0cHM6Ly93d3cudmVy\n"
           + "aXNpZ24uY29tL2NwczAoBgNVHSUEITAfBggrBgEFBQcDAQYIKwYBBQUHAwIGCWCG\n"
           + "SAGG+EIEATAfBgNVHSMEGDAWgBROQ8gddu83U3pP8lhvlPM44tW93zB2BggrBgEF\n"
           + "BQcBAQRqMGgwKwYIKwYBBQUHMAGGH2h0dHA6Ly9FVkludGwtb2NzcC52ZXJpc2ln\n"
           + "bi5jb20wOQYIKwYBBQUHMAKGLWh0dHA6Ly9FVkludGwtYWlhLnZlcmlzaWduLmNv\n"
           + "bS9FVkludGwyMDA2LmNlcjANBgkqhkiG9w0BAQUFAAOCAQEAUh48IWs1csaAU3kK\n"
           + "hOZV4vde2ECxgVc0gRNz4V5fVdLsFv04S0V4pSZX77rQn56CFNkj6eImdAaTJVbd\n"
           + "Wk8bB2FIwhjNnWScPXuNxzigVOpfRGuNRJymvkqG1+wq4BlG6aXa8aGu7aiuBCqN\n"
           + "rmRSCj5WZQ94K3NCBUIiQQ9Ll1OGOYO3EM/rylGqUcnPf5aSET2kCIBfN3sG6veH\n"
           + "wex+op4GuETJ48+PCoP0d1WrGGGs++nAgBYjjGCZciYfIxoqyrVaC5Yt5iYpXZA0\n"
           + "ZzqJNbzmUD/l2rJeakdAHK0XYPwbQqvNvI1+dUNR9jlRxSKR8XX6mPe5ZgzMqYu+\n"
           + "CQTDhg==\n"
           + "-----END CERTIFICATE-----\n");

    private static final String CERT_WITH_BASIC_CA_ZERO_PATH_LENGTH
        = ("-----BEGIN CERTIFICATE-----\n"
           + "MIIGqTCCBJGgAwIBAgIJAPeSt8SBjARYMA0GCSqGSIb3DQEBBQUAMEUxCzAJBgNV\n"
           + "BAYTAkNIMRUwEwYDVQQKEwxTd2lzc1NpZ24gQUcxHzAdBgNVBAMTFlN3aXNzU2ln\n"
           + "biBHb2xkIENBIC0gRzIwHhcNMDkwNjEwMDkyOTM5WhcNMjQwNjA2MDkyOTM5WjBN\n"
           + "MQswCQYDVQQGEwJDSDEVMBMGA1UEChMMU3dpc3NTaWduIEFHMScwJQYDVQQDEx5T\n"
           + "d2lzc1NpZ24gRVYgR29sZCBDQSAyMDA5IC0gRzIwggEiMA0GCSqGSIb3DQEBAQUA\n"
           + "A4IBDwAwggEKAoIBAQDQnYs8uZZJHHloM5ucf7q7XcRN1Bl8QMoZiruC8oPmghom\n"
           + "gZyb1qF0nAU/qx13UhcGWrV0goF/2Z8nMUGHjSeHuU65AS6rxm83XvnyI7rLKEcg\n"
           + "4XXgibW3+bKldwjYfgPujGrZXC8gwx3jA+uF35VMIYpkWayAbl6kmoIsN7s7ZOVw\n"
           + "T9gRIyZ+GVhFGgmeYGlUYEY1dQ66nMhwQQtTfVcMIiJPbBnppxU+5D0LM7vOwRX8\n"
           + "tsEOVZyojP3bDqtHo/iWkeMPYSazOEdq4BB0QSc1mXVnu9Vh/NjBm00d0Agd/KsQ\n"
           + "Nn/pR+tbgUYkiBhnu3oJ+XFNBsyFrOxGLJkg9P6fAgMBAAGjggKSMIICjjAOBgNV\n"
           + "HQ8BAf8EBAMCAQYwEgYDVR0TAQH/BAgwBgEB/wIBADAdBgNVHQ4EFgQUiHRGbcd8\n"
           + "tfpycRnevydeDWjg9ycwHwYDVR0jBBgwFoAUWyV7lqRlUX64OfPAeGZe6Drn8O4w\n"
           + "gf8GA1UdHwSB9zCB9DBHoEWgQ4ZBaHR0cDovL2NybC5zd2lzc3NpZ24ubmV0LzVC\n"
           + "MjU3Qjk2QTQ2NTUxN0VCODM5RjNDMDc4NjY1RUU4M0FFN0YwRUUwgaiggaWggaKG\n"
           + "gZ9sZGFwOi8vZGlyZWN0b3J5LnN3aXNzc2lnbi5uZXQvQ049NUIyNTdCOTZBNDY1\n"
           + "NTE3RUI4MzlGM0MwNzg2NjVFRTgzQUU3RjBFRSUyQ089U3dpc3NTaWduJTJDQz1D\n"
           + "SD9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0P2Jhc2U/b2JqZWN0Q2xhc3M9Y1JM\n"
           + "RGlzdHJpYnV0aW9uUG9pbnQwXQYDVR0gBFYwVDBSBgRVHSAAMEowSAYIKwYBBQUH\n"
           + "AgEWPGh0dHA6Ly9yZXBvc2l0b3J5LnN3aXNzc2lnbi5jb20vU3dpc3NTaWduLUdv\n"
           + "bGQtQ1AtQ1BTLVI0LnBkZjCBxgYIKwYBBQUHAQEEgbkwgbYwZAYIKwYBBQUHMAKG\n"
           + "WGh0dHA6Ly9zd2lzc3NpZ24ubmV0L2NnaS1iaW4vYXV0aG9yaXR5L2Rvd25sb2Fk\n"
           + "LzVCMjU3Qjk2QTQ2NTUxN0VCODM5RjNDMDc4NjY1RUU4M0FFN0YwRUUwTgYIKwYB\n"
           + "BQUHMAGGQmh0dHA6Ly9vY3NwLnN3aXNzc2lnbi5uZXQvNUIyNTdCOTZBNDY1NTE3\n"
           + "RUI4MzlGM0MwNzg2NjVFRTgzQUU3RjBFRTANBgkqhkiG9w0BAQUFAAOCAgEARJJo\n"
           + "SpTCFSg5U+D4W8Cdc7vxEr83McOZY+D1fX490SAv3sDJ7XcbdXODL5m4UeK4s4bg\n"
           + "UR1ZgCFiK8A4GRFpIvD4qse8E+Z20PGbQmtlSUIJztL3y3y4hLcM2Vt+mZz7M+aN\n"
           + "xVlFbIrje+3PwgnvDTrIOLNt+LtV/uonA4A9SpAxlUCroFfSpfA71a3SJll/C4OG\n"
           + "uvPZjHuX1ResF91+JJoyCiHcdi9h6w0yEf29zXdzKkUsaOZ0CikPTKdCZQ4MbIGX\n"
           + "D5qMY65PK0mpT7uAt93ZIITXfQs93RWJWZtF7HrHGjIeloeKkXofsylmqP3JfgeV\n"
           + "/mjuYz/9HS5MAxVE5+Wcb08tMGaoqSRxYhnv2Tmx2s8mPHyCXocgxMhXJtCN++Ba\n"
           + "oO7JQRXeoiUZzIMac67dWb3rScOtEdF4lkIWB0yyts6LUPJtXXbRog3EI3i65ofc\n"
           + "nW3ZdQijbE5t3F03yY/qRoHO8I/Be3qe1zk+7FCpjx7B8VLB1+lajfvLml0sgvCY\n"
           + "O/O9/RRmqFhdhfDnsPj/pWkM6nKu8KjXX6WZmW6FTuC57yG81dI2AYqoO3qlzDdt\n"
           + "IgVXouBar3TAgWRIka5FsxudaWOUK+Mj9TiKSQBYglHWhkdlEUpjOZfZhHKkMht4\n"
           + "Y5mbkvu5+9xcWGhKNBLBq/isdBPkyfLVeVWxxkQ=\n"
           + "-----END CERTIFICATE-----\n");

    private static final String CERT_WITH_BASIC_CA_NO_PATH_LENGTH
        = ("-----BEGIN CERTIFICATE-----\n"
           + "MIIFujCCA6KgAwIBAgIJALtAHEP1Xk+wMA0GCSqGSIb3DQEBBQUAMEUxCzAJBgNV\n"
           + "BAYTAkNIMRUwEwYDVQQKEwxTd2lzc1NpZ24gQUcxHzAdBgNVBAMTFlN3aXNzU2ln\n"
           + "biBHb2xkIENBIC0gRzIwHhcNMDYxMDI1MDgzMDM1WhcNMzYxMDI1MDgzMDM1WjBF\n"
           + "MQswCQYDVQQGEwJDSDEVMBMGA1UEChMMU3dpc3NTaWduIEFHMR8wHQYDVQQDExZT\n"
           + "d2lzc1NpZ24gR29sZCBDQSAtIEcyMIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIIC\n"
           + "CgKCAgEAr+TufoskDhJuqVAtFkQ7kpJcyrhdhJJCEyq8ZVeCQD5XJM1QiyUqt2/8\n"
           + "76LQwB8CJEoTlo8jE+YoWACjR8cGp4QjK7u9lit/VcyLwVcfDmJlD909Vopz2q5+\n"
           + "bbqBHH5CjCA12UNNhPqE21Is8w4ndwtrvxEvcnifLtg+5hg3Wipy+dpikJKVyh+c\n"
           + "6bM8K8vzARO/Ws/BtQpgvd21mWRTuKCWs2/iJneRjOBiEAKfNA+k1ZIzUd6+jbqE\n"
           + "emA8atufK+ze3gE/bk3lUIbLtK/tREDFylqM2tIrfKjuvqblCqoOpd8FUrdVxyJd\n"
           + "MmqXl2MT28nbeTZ7hTpKxVKJ+STnnXepgv9VHKVxaSvRAiTysybUa9oEVeXBCsdt\n"
           + "MDeQKuSeFDNeFhdVxVu1yzSJkvGdJo+hB9TGsnhQ2wwMC3wLjEHXuendjIj3o02y\n"
           + "MszYF9rNt85mndT9Xv+9lz4pded+p2JYryU0pUHHPbwNUMoDAw8IWh+Vc3hiv69y\n"
           + "FGkOpeUDDniOJihC8AcLYiAQZzlG+qkDzAQ4embvIIO1jEpWjpEA/I5cgt6IoMPi\n"
           + "aG59je883WX0XaxR7ySArqpWl2/5rX3aYT+YdzylkbYcjCbaZaIJbcHiVOO5ykxM\n"
           + "gI93e2CaHt+28kgeDrpOVG2Y4OGiGqJ3UM/EY5LsRxmd6+ZrzsECAwEAAaOBrDCB\n"
           + "qTAOBgNVHQ8BAf8EBAMCAQYwDwYDVR0TAQH/BAUwAwEB/zAdBgNVHQ4EFgQUWyV7\n"
           + "lqRlUX64OfPAeGZe6Drn8O4wHwYDVR0jBBgwFoAUWyV7lqRlUX64OfPAeGZe6Drn\n"
           + "8O4wRgYDVR0gBD8wPTA7BglghXQBWQECAQEwLjAsBggrBgEFBQcCARYgaHR0cDov\n"
           + "L3JlcG9zaXRvcnkuc3dpc3NzaWduLmNvbS8wDQYJKoZIhvcNAQEFBQADggIBACe6\n"
           + "45R88a7A3hfm5djV9VSwg/S7zV4Fe0+fdWavPOhWfvxyeDgD2StiGwC5+OlgzczO\n"
           + "UYrHUDFu4Up+GC9pWbY9ZIEr44OE5iKHjn3g7gKZYbge9LgriBIWhMIxkziWMaa5\n"
           + "O1M/wySTVltpkuzFwbs4AOPsF6m43Md8AYOfMke6UiI0HTJ6CVanfCU2qT1L2sCC\n"
           + "bwq7EsiHSycR+R4tx5M/nttfJmtS2S6K8RTGRI0Vqbe/vd6mGu6uLftIdxf+u+yv\n"
           + "GPUqUfA5hJeVbG4bwyvEdGB5JbAKJ9/fXtI5z0V9QkvfsywexcZdylU6oJxpmo/a\n"
           + "77KwPJ+HbBIrZXAVUjEaJM9vMSNQH4xPjyPDdEFjHFWoFN0+4FFQz/EbMFYOkrCC\n"
           + "hdiDyyJkvC24JdVUorgG6q2SpCSgwYa1ShNqR88uC1aVVMvOmttqtKay20EIhid3\n"
           + "92qgQmwLOM7XdVAyksLfKzAiSNDVQTglXaTpXZ/GlHXQRf0wl0OPkKsKx4ZzYEpp\n"
           + "Ld6leNcG2mqeSz53OiATIgHQv2ieY2BrNU0LbbqhPcCT4H8js1WtciVORvnSFu+w\n"
           + "ZMEBnunKoGqYDs/YYPIvSbjkQuE4NRb0yG5P94FW6LqjviOvrv1vA+ACOzB2+htt\n"
           + "Qc8Bsem4yWb02ybzOqR08kkkW8mw0FfB+j564ZfJ\n"
           + "-----END CERTIFICATE-----\n");

    public void testGetBasicConstraints() throws Exception {
        // iOS Security Framework only generates certificates for with correct constraints.
        assertEquals(5, generateCert(CERT_CORRECT).getBasicConstraints());
        //assertEquals(-1, generateCert(CERT_WITHOUT_BASIC).getBasicConstraints());
        //assertEquals(-1, generateCert(CERT_WITH_BASIC_NON_CA).getBasicConstraints());
        //assertEquals(0, generateCert(CERT_WITH_BASIC_CA_ZERO_PATH_LENGTH).getBasicConstraints());
        //assertEquals(Integer.MAX_VALUE, generateCert(CERT_WITH_BASIC_CA_NO_PATH_LENGTH).getBasicConstraints());
    }

    public void testCertificateException() throws Exception {
        try {
            generateCert(CERT_TAMPERED);
            fail();
        } catch (CertificateException expected) {
        }

        try {
            generateCert(CERT);
            fail();
        } catch (CertificateException expected) {
        }
    }

    public X509Certificate generateCert(String string) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream bais = new ByteArrayInputStream(string.getBytes());
        X509Certificate cert = (X509Certificate) cf.generateCertificate(bais);
        assertNotNull(cert);
        return cert;
    }
}
