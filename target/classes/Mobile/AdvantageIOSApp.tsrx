<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>AdvantageIOSApp</className>
    <objects>
        <object type="Application" technology="Mobile">
            <name>Advantage Shopping</name>
            <codeName>AdvantageShoppingApplication</codeName>
            <identification>
                <properties>
                    <property name="Identifier" type="string" isRegExp="false">com.hpe.advantage</property>
                    <property name="IsPackaged" type="boolean" isRegExp="false">false</property>
                </properties>
            </identification>
            <children>
                <object type="Button" technology="Mobile">
                    <name>Menu</name>
                    <codeName>MenuButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Menu</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Menu</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>LOG IN</name>
                    <codeName>LOGIN</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">LOG IN</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">LOG IN</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>LAPTOPS</name>
                    <codeName>LAPTOPSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">LAPTOPS</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">LAPTOPS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][3][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>HEADPHONES</name>
                    <codeName>HEADPHONESLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">HEADPHONES</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">HEADPHONES</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][4][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>TABLETS</name>
                    <codeName>TABLETSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">TABLETS</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">TABLETS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][5][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SPEAKERS</name>
                    <codeName>SPEAKERSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SPEAKERS</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">SPEAKERS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][6][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>LOGINObj</name>
                    <codeName>LOGINObjUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">LOG IN</property>
                            <property name="ClassName" type="string" isRegExp="false">TableCell</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>UserName</name>
                    <codeName>UserNameEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">username</property>
                            <property name="Text" type="string" isRegExp="false">Username</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>LOGIN</name>
                    <codeName>LOGINButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">LOGIN</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">LOGIN</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
