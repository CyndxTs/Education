function Initialize()
	-- 
	local w = {}
	local j = 1
	local ini = io.input(SKIN:ReplaceVariables(SELF:GetOption("ReadFile"))):read("*all")
	local gsub = string.gsub
	local Sub = SELF:GetOption("Sub")
	local Index = SKIN:ParseFormula(SELF:GetNumberOption("Index"))
	local Limit = SKIN:ParseFormula(SELF:GetNumberOption("Limit"))
	--
	for i=Index,Limit do
		w[j]=gsub(ini,Sub,i)
		j=j+1
	end
	--
	local f=io.open(SKIN:ReplaceVariables(SELF:GetOption("WriteFile")),"w")
	f:write(table.concat(w,"\n\n"))
	f:close()
end
