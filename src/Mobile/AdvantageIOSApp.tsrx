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
                    <property name="Identifier" type="string" isRegExp="false">com.Advantage.iShopping</property>
                    <property name="IsPackaged" type="boolean" isRegExp="false">true</property>
                </properties>
            </identification>
            <children>
                <object type="Button" technology="Mobile">
                    <name>Menu</name>
                    <codeName>MenuButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Menu</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>HOME</name>
                    <codeName>HOMELabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">HOME</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">HOME</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][1][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
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
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
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
                <object type="Button" technology="Mobile">
                    <name>User settings</name>
                    <codeName>UserSettingsButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">User settings</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>setting</tag>
                    </tags>
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
                <object type="Label" technology="Mobile">
                    <name>CART</name>
                    <codeName>CARTLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">CART</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">CART</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][8][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SETTINGS</name>
                    <codeName>SETTINGSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SETTINGS</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">SETTINGS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][9][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>Login Obj</name>
                    <codeName>LoginObj</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">TableCell</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>USER NAME login</name>
                    <codeName>UserNameLoginditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">USER NAME</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>PASSWORD login</name>
                    <codeName>PasswordLoginEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
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
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>UserNameSignUp</name>
                    <codeName>UserNameSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">* USER NAME</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>EmailSignUp</name>
                    <codeName>EmailSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>PasswordSignUp</name>
                    <codeName>PasswordSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>confirnPasswordSignUp</name>
                    <codeName>ConfirnPasswordSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>StreetSignUp</name>
                    <codeName>StreetSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">STREET</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>CitySignUp</name>
                    <codeName>CitySignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">CITY</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="ResourceId" type="string" isRegExp="false"></property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>ZIPSignUp</name>
                    <codeName>ZIPSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">ZIP</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>REGISTER</name>
                    <codeName>REGISTERButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">REGISTER</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">REGISTER</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>SignUp</name>
                    <codeName>SignUpButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Don't have an account? SIGN UP TODAY</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Invalid user name or pas</name>
                    <codeName>InvalidUserNameOrPasLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Invalid user name or password</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Invalid user name or password</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>MenuObj</name>
                    <codeName>MenuObjUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">14</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>login</name>
                    <codeName>LoginLabel</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>YesSignOut</name>
                    <codeName>YesSignOutButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Yes</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Yes</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>UseMylocation</name>
                    <codeName>UseMylocationUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">22</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Use my location</name>
                    <codeName>UseMyLocationLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Use my location</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Use my location</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">11</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>SpeakerImg</name>
                    <codeName>SpeakerImgUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="Container" type="string" isRegExp="false">CollectionView[0][2][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>purchase</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>color</name>
                    <codeName>ColorButton</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>purchase</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>ADD TO CART</name>
                    <codeName>ADDTOCARTButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">ADD TO CART</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">ADD TO CART</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>colorObj</name>
                    <codeName>ColorObj</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="Container" type="string" isRegExp="false">CollectionView[0][2][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>carticon</name>
                    <codeName>CarticonButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">cartItem</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>cart</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>CHECKOUT</name>
                    <codeName>CHECKOUTButton</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="Text" type="string" isRegExp="false"></property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>paymentEdit</name>
                    <codeName>PaymentEditUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>APPLY</name>
                    <codeName>APPLYButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">APPLY</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">APPLY</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>*SAFEPAY USERNAME</name>
                    <codeName>SAFEPAYUSERNAMEEditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">*SAFEPAY USERNAME</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>*SAFEPAY PASSWORD</name>
                    <codeName>SAFEPAYPASSWORDEditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">*SAFEPAY PASSWORD</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Save SafePAy credentials</name>
                    <codeName>SaveSafePAyCredentialsLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Save SafePAy credentials in your profile</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Save SafePAy credentials in your profile</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>PAY NOW</name>
                    <codeName>PAYNOWButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">PAY NOW</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">PAY NOW</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>Ok</name>
                    <codeName>OkButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Ok</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Ok</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>OrderObj</name>
                    <codeName>OrderObj</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false"></property>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">14</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>*CARD NUMBER</name>
                    <codeName>CARDNUMBEREditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">*CARD NUMBER</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>*CVV NUMBER</name>
                    <codeName>CVVNUMBEREditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>*CARDHOLDER NAME</name>
                    <codeName>CARDHOLDERNAMEEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Save MasterCredit creden</name>
                    <codeName>SaveMasterCreditCredenLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Save MasterCredit credentials in your profile</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Save MasterCredit credentials in your profile</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>Done</name>
                    <codeName>DoneButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Done</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Done</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="DropDown" technology="Mobile">
                    <name>MobileDropDown</name>
                    <codeName>CountryDropDown</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ItemSelector</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Country</name>
                    <codeName>CountryLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Country</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Country</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>Plus</name>
                    <codeName>PlusButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Plus</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>leptop Item</name>
                    <codeName>LeptopItem</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="Container" type="string" isRegExp="false">CollectionView[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="DropDown" technology="Mobile">
                    <name>YearDropDown</name>
                    <codeName>YearDropDown</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ItemSelector</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="DropDown" technology="Mobile">
                    <name>MonthDropDown</name>
                    <codeName>MonthDropDown</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false"></property>
                            <property name="ClassName" type="string" isRegExp="false">ItemSelector</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>* mm</name>
                    <codeName>MmLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">* mm</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">* mm</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">5</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>* yyyy</name>
                    <codeName>YyyyLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">* yyyy</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">* yyyy</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>SoldOut</name>
                    <codeName>SoldOutUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="Container" type="string" isRegExp="false">CollectionView[0][1][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>quantity button</name>
                    <codeName>QuantityButton</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
