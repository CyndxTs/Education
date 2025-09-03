[Icon_ID]
Meter=Image
ImageName=#@#Images\#AppImg_ID#
LeftMouseUpAction=!Execute [!CommandMeasure Animate "interact(ID)"]
MouseOverAction=[!CommandMeasure "Animate" "highlight(ID)"]
MeterStyle=LayoutIcon
Group=Icons

[Label_ID]
Meter=String
Text=#AppLbl_ID#
FontColor=255,255,255,255
FontSize=#label_fontsize#
AntiAlias=1
StringAlign=Left
InlineSetting=None
DynamicVariables=1
Group=Labels
Hidden=1
MouseOverAction=[!ShowMeter Label_ID][!Redraw]
MouseLeaveAction=[!HideMeter Label_ID][!Redraw]