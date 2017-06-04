<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>DotNetAppModel</className>
    <objects>
        <object type="Window" technology="WPF">
            <name>Advantage Shop Administrator</name>
            <codeName>AdvantageShopAdministrator</codeName>
            <identification>
                <properties>
                    <property name="ObjectName" type="string" isRegExp="false">Advantage Shop Administrator</property>
                    <property name="FullType" type="string" isRegExp="false">window</property>
                    <property name="WindowTitleRegExp" type="string" isRegExp="false">Advantage Shop Administrator</property>
                </properties>
            </identification>
            <children>
                <object type="List" technology="WPF">
                    <name>TopBar</name>
                    <codeName>TopBarList</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">list view</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Advantage Shop Administrator</property>
                        </properties>
                        <index>0</index>
                    </identification>
                    <tags>
                        <tag>top</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>User Name</name>
                    <codeName>UserNameEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;</property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Advantage Shop Administrator</property>
                        </properties>
                        <index>0</index>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>Password</name>
                    <codeName>PasswordEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">Password_Box</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>Server</name>
                    <codeName>ServerEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;</property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Advantage Shop Administrator</property>
                        </properties>
                        <index>1</index>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="CheckBox" technology="WPF">
                    <name>Remember me</name>
                    <codeName>RememberMeCheckBox</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Remember me</property>
                            <property name="ObjectName" type="string" isRegExp="false">Remember me</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="Button" technology="WPF">
                    <name>SIGN IN</name>
                    <codeName>SIGNINButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SIGN IN</property>
                            <property name="ObjectName" type="string" isRegExp="false">SIGN IN</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="WPF">
                    <name>admin</name>
                    <codeName>AdminUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;</property>
                            <property name="FullType" type="string" isRegExp="false">object</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Advantage Shop Administrator</property>
                        </properties>
                        <index>1</index>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="Button" technology="WPF">
                    <name>Logout</name>
                    <codeName>LogoutButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Logout</property>
                            <property name="ObjectName" type="string" isRegExp="false">Logout</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="WPF">
                    <name>PRODUCTS </name>
                    <codeName>PRODUCTSUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;</property>
                            <property name="FullType" type="string" isRegExp="false">object</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Advantage Shop Administrator</property>
                        </properties>
                        <index>1</index>
                    </identification>
                    <tags>
                        <tag>main</tag>
                    </tags>
                </object>
                <object type="List" technology="WPF">
                    <name>SideList</name>
                    <codeName>SideList</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;</property>
                            <property name="FullType" type="string" isRegExp="false">list view</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Advantage Shop Administrator</property>
                        </properties>
                    </identification>
                </object>
                <object type="Table" technology="WPF">
                    <name>ProductsDataGrid</name>
                    <codeName>ProductsDataGridTable</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">ProductsDataGrid</property>
                        </properties>
                    </identification>
                </object>
                <object type="TabStrip" technology="WPF">
                    <name>ProductsWpfTabStrip</name>
                    <codeName>ProductsWpfTabStrip</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">tab</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="WPF">
                    <name>ADD COLORS</name>
                    <codeName>ADDCOLORSButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">ADD COLORS</property>
                            <property name="ObjectName" type="string" isRegExp="false">addColorButton</property>
                        </properties>
                    </identification>
                </object>
                <object type="CheckBox" technology="WPF">
                    <name>YelloeCheckBox</name>
                    <codeName>YelloeCheckBox</codeName>
                    <identification>
                        <properties>
                            <property name="ParentText" type="string" isRegExp="false"></property>
                            <property name="Text" type="string" isRegExp="false"></property>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">check box</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">Advantage Shop Administrator</property>
                        </properties>
                        <index>5</index>
                    </identification>
                </object>
                <object type="Button" technology="WPF">
                    <name>SAVE</name>
                    <codeName>SAVEButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SAVE</property>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                        </properties>
                    </identification>
                </object>
                <object type="Table" technology="WPF">
                    <name>ProductsColorsGrid</name>
                    <codeName>ProductsColorsGridTable</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">ProductsColorsGrid</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
