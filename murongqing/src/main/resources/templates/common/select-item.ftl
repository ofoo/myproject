<option value="${selectItem.value}">${selectItem.name}</option>
<#list selectItem.selectItemList as item>
<option value="${item.value}">${item.name}</option>
</#list>