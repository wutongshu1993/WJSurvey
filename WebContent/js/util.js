/**
 * 
 */
function matchesSelector(element, selector){
	if(element.matchesSelector){
		return element.matchesSelector(selector);
	}
	else if(element.msMatchesSelector){
		return element.msMatchesSelector(selector);
	}
	else if(element.mozMatchesSelector){
		return element.mozMatchesSelector(selector);
	}
	else if(element.webkitMatchesSelector){
		return element.webkitMatchesSelector(selector);
	}
	else {
		throw new Error("not supported");
	}
}