var model = new Array(); 

function init_tag(){ 
    var layer1 = document.getElementById('layer1'); 
    var layer2 = document.getElementById('layer2'); 
    var layer3 = document.getElementById('layer3'); 

    model.push(layer1); 
    model.push(layer2); 
    model.push(layer3); 

    for(var i=0,max=model.length; i<max; i++){ 
        set_event_listener(model[i],'mouseover','switching_tab'); 
    } 

    switch_execute(layer1); 
} 

function set_event_listener(obj,evt_type,callback){ 
        obj.addEventListener(evt_type,eval(callback),false);     
} 

function switching_tab(e){ 
    var target; 
    target = e.target; 

    switch_execute(target.parentNode); 
} 

function switch_execute(layer){ 
    var visible_tab_top = -1; 
    var hidden_tab_top = 0; 

    if(window.navigator.appName.indexOf('Explorer') > -1){ 
        visible_tab_top++; 
        hidden_tab_top++; 
    } 

    for(var i=0,max=model.length; i<max; i++){ 
        var tab = get_tab_element(model[i]); 
        var content = get_content_element(model[i]); 

        if(model[i] == layer){ 
            model[i].style.zIndex = 99; 
            tab.style.top = visible_tab_top; 
            tab.style.backgroundColor = '#FFFFFF'; 
            content.style.backgroundColor = '#FFFFFF'; 
        }else{ 
            model[i].style.zIndex = 0; 
            tab.style.top = hidden_tab_top; 
            tab.style.backgroundColor = '#EEEEEE'; 
            content.style.backgroundColor = '#EEEEEE'; 
        } 
    } 
} 

function get_tab_element(model){ 
    var child = model.childNodes; 

    for(var i=0,max=child.length; i<max; i++){ 
        if(child[i].nodeType == 1 && child[i].className == 'tab'){ 
            return child[i]; 
        } 
    } 
} 

function get_content_element(model){ 
    var child = model.childNodes; 

    for(var i=0,max=child.length; i<max; i++){ 
        if(child[i].nodeType == 1 && child[i].className == 'content'){ 
            return child[i]; 
        } 
    } 
} 