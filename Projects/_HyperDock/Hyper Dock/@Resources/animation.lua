function Initialize()
	isLock = false
	interacted = 0
	count = tonumber(SKIN:GetVariable('TotalApps'))
	skinwidth = tonumber(SKIN:GetVariable('CURRENTCONFIGWIDTH'))
	skinheight = tonumber(SKIN:GetVariable('CURRENTCONFIGHEIGHT'))
	divider = SKIN:GetVariable('divider')
	vertical = tonumber(SKIN:GetVariable('vertical'))
	hide = 0
	show = 0
	width=tonumber(SKIN:GetVariable('icon_width'))
	height=tonumber(SKIN:GetVariable('icon_height'))
	padding=tonumber(SKIN:GetVariable('padding'))
	expand=tonumber(SKIN:GetVariable('expand'))
	select=0
	lastSelect = 0
	hideicon=tonumber(SKIN:GetVariable('autohide'))
	direction = tonumber(SKIN:GetVariable('direction'))
	edit = 0
	edit_entry = 0
	meter = {}
	x = {}
	y = {}
	w = {}
	h = {}
	xto = {}
	yto = {}
	if vertical == 1 then
		if direction < 0 then start = -height else start = skinwidth end
		for i=1, count do
			meter[i]=SKIN:GetMeter('Icon_'..i)
			if meter[i] == nil then SKIN:Bang("!Refresh") end
			xto[i]= -width+(skinwidth/2)+(skinwidth/2)*(1+direction)
			yto[i]= (skinheight/2)
			y[i]= yto[i]
			x[i]= xto[i]
			w[i] = 1
			h[i] = 1
			SKIN:Bang("!SetOption","Icon_"..i, "Padding", padding/2 .. ',' .. padding/2 .. ',' .. padding/2 .. ',' .. padding/2)
			end
	else
		for i=1, count do
			if direction < 0 then start = -height else start = skinheight end
			meter[i]=SKIN:GetMeter('Icon_'..i)
			if meter[i] == nil then SKIN:Bang("!Refresh") end
			xto[i]= (skinwidth/2)
			yto[i]= -height+(skinheight/2)+(skinheight/2)*(1+direction)
			y[i]= yto[i]
			x[i]= xto[i]
			w[i] = 1
			h[i] = 1
			SKIN:Bang("!SetOption","Icon_"..i, "Padding", padding/2 .. ',' .. padding/2 .. ',' .. padding/2 .. ',' .. padding/2)
		end
	end
end

function animate()
    lock_ani()
    
    -- Animación para TODOS los íconos
    for i = 1, count do
        -- Expansión del ícono seleccionado
        if i == select then
            w[i] = w[i] - (w[i] - (width * expand - padding)) / divider
            h[i] = h[i] - (h[i] - (height * expand - padding)) / divider
        else
            -- Tamaño normal para los demás
            w[i] = w[i] - (w[i] - (width - padding)) / divider
            h[i] = h[i] - (h[i] - (height - padding)) / divider
        end

        -- Suavizar movimiento (interpolación)
        x[i] = x[i] - (x[i] - xto[i]) / (divider + 1)
        y[i] = y[i] - (y[i] - yto[i]) / divider

        -- Aplicar cambios
        meter[i]:SetX(x[i])
        meter[i]:SetY(y[i])
        SKIN:Bang("!SetOption", "Icon_"..i, "W", w[i])
        SKIN:Bang("!SetOption", "Icon_"..i, "H", h[i])
        meter[i]:Show()

        -- Mostrar etiqueta solo en el ícono seleccionado
        local label = SKIN:GetMeter("Label_"..i)
        if label then
            label:SetX(x[i] + w[i] + 10)
            label:SetY(y[i] + h[i]/2 - 12)
            if i == select then 
                label:Show()
            else 
                label:Hide()
            end
        end
    end

    unlock_ani()
end

function update()
	if vertical == 1 then update_vertical() else update_horizonal() end
end

function update_vertical()
	local xs = 0
	local xx = (skinheight/2) - (show * height / 2)
	local d = 1
	if select == 0 then
		for i=1, show do
			if i <= hide then xto[i] = start else xto[i]=((expand-1)*height)/2 end
			yto[i]=xx
			xx=xx + height
		end
	else
		for i=1, show do
			if i == select then 
				xto[i] = 0
				yto[i] = xx - ((expand - 1) * height) / 2
			else
				xto[i] = ((expand - 1) * height) / 2
				yto[i] = xx + ((i < select) and -1 or 1) * (expand - 1) * height / 2
			end

			if i <= hide then
				xto[i] = start
			end

			xx = xx + height
		end
	end
end

function update_horizonal()
	local xs = (show-select)/2
	local xx = (skinwidth/2) - (show * width / 2)
	local d = 1
	if select == 0 then
		for i=1, show do
			if i <= hide then yto[i] = start else yto[i]=((expand-1)*width)/2 end
			xto[i]=xx
			xx=xx + (width*(show-i))*d
			d = d*(-1)
		end
	else
		for i=1, show do
			if i == select then 
				yto[i]=0
				xto[i]=xx - ((expand-1)*width)/2
			else
				yto[i]=((expand-1)*width)/2
			if -d*((show-i)/2)<xs then
				xto[i]= xx - (expand-1)*width/2
			else
				xto[i]= xx + (expand-1)*width/2
			end
		end
		if i <= hide then yto[i]=start end
		xx=xx + (width*(show-i))*d
		d = d*(-1)
		end
	end
end

function show_more()
	if show < count then
		show = show+1
		update()
	end
end

function highlight(i)
	if isLock then return end
	interacted = i
	if i>0 then edit_entry = i end
	if edit == 0 then
		select = i
		update()
	end
end

--
function interact(index)
	local command=SKIN:GetVariable('AppDir_'..index)
	if string.match(command, '%[') then
		SKIN:Bang(command)
	elseif string.match(command, '"') then
		SKIN:Bang('"'..command..'"')
	else
		SKIN:Bang('"'..command..'"')
	end
end

function hide_icon()
	if hide < count and hideicon == 1 and edit == 0 then
		hide = hide + 1
		update()
	end
end	

function unhide_icon()
	if hide > 0 and hideicon == 1 and edit == 0 then
		hide = hide - 1
		update()
	end
end	

function lock_ani()
	isLock = true
end

function unlock_ani()
	isLock = false
end

function init_all()
	lock_ani()
	SKIN:Bang('[!CommandMeasure Animation "Execute 2"]')
	unlock_ani()
	SKIN:Bang('[!CommandMeasure Animation "Execute 3"]')
end

function hide_all()
	if interacted > 0 then
		SKIN:Bang('[!CommandMeasure Animation "Stop 4"]')
		SKIN:Bang('[!CommandMeasure Animation "Stop 3"]')
	else
		SKIN:Bang('[!CommandMeasure Animation "Execute 4"]')
	end
end

function edit_icon()
	if edit == 0 then
		edit = 1
		show = count
		hide = 0
		select = 0
		update()
		SKIN:Bang("!EnableMeasureGroup", "DropGroup")
		SKIN:Bang("!ShowMeter","Debug")
		SKIN:Bang("!SetOption", "Debug", "Text", "Edit enabled")
	else 
		edit = 0
		SKIN:Bang("!DisableMeasureGroup", "DropGroup")
		SKIN:Bang("!HideMeter","Debug")
		update()
	end
end

function add_icon()
	SKIN:Bang("!WriteKeyValue", "Variables", 'TotalApps', count+1, "#@#Applist.inc")
	SKIN:Bang("!WriteKeyValue", "Variables", 'AppImg_'..count+1, 'new.png', "#@#Applist.inc")
	SKIN:Bang("!WriteKeyValue", "Variables", 'AppDir_'..count+1, 'ms-settings:', "#@#Applist.inc")
	SKIN:Bang("!WriteKeyValue", "Variables", 'AppLbl_'..count+1, 'NewApp', "#@#Applist.inc")
	SKIN:Bang("!Refresh")
end

function remove_icon()
	local newCount = count-1
	for i=edit_entry, newCount do
		local AppImg_=SKIN:GetVariable('AppImg_'..i+1)
		local AppDir_=SKIN:GetVariable('AppDir_'..i+1)
		local AppLbl_=SKIN:GetVariable('AppLbl_'..i+1)
		SKIN:Bang("!WriteKeyValue", "Variables", 'AppImg_'..i, AppImg_, "#@#Applist.inc")
		SKIN:Bang("!WriteKeyValue", "Variables", 'AppDir_'..i, AppDir_, "#@#Applist.inc")
		SKIN:Bang("!WriteKeyValue", "Variables", 'AppLbl_'..i, AppLbl_, "#@#Applist.inc")
	end
	
	SKIN:Bang("!WriteKeyValue", "Variables", 'TotalApps', newCount, "#@#Applist.inc")
	
	SKIN:Bang("!Refresh")
end


