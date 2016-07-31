var app = angular.module('app', ['ui.grid', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap']);

app.controller('ModalInstanceCtrl', function ($scope, $modalInstance, message) {
	$scope.message = message;
	$scope.ok = function () {
		$modalInstance.dismiss('ok');
		window.scroll(0, 0);
	};
	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
});

app.directive('numbersOnly', function(){
	return {
		require: 'ngModel',
		link: function(scope, element, attrs, modelCtrl) {
			modelCtrl.$parsers.push(function (inputValue) {
				if (inputValue == undefined) return '' 
				var transformedInput = inputValue.replace(/[^0-9]/g, ''); 
				if (transformedInput!=inputValue) {
					modelCtrl.$setViewValue(transformedInput);
					modelCtrl.$render();
				}
				return transformedInput;
			});
		}
	};
});

app.directive('decimalOnly', function(){
	return {
		require: 'ngModel',
		link: function(scope, element, attrs, modelCtrl) {
			modelCtrl.$parsers.push(function (inputValue) {
				if (inputValue == undefined || inputValue == "" || inputValue == null) {
					scope.decimalError = false;
					return '';
				}

				var regex = new RegExp('^[0-9]{1,10},[0-9][0-9]$');
				var transformedInput = regex.exec(inputValue);
				
				if (!transformedInput) {
					scope.decimalError = true;
					modelCtrl.$render();
				} else {
					scope.decimalError = false;
					modelCtrl.$render();
				}
				return inputValue;
			});
		}
	};
});

app.controller('controllerMenu', ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
	
	$scope.getSessionLocal = function () {
		$http.get('/controlIT/rest/token/session').success(function (data, status) {
			if (data != null && data != "") {
				
				$scope.name_user = data.name;
				$scope.gender = data.gender;
				
				for(var i=0; i < data.systemProfiles.length; i++){
					if(data.systemProfiles[i].system.id === 7){
						
						$scope.name_profile = data.systemProfiles[i].profile.name;
						for(var x=0; x < data.systemProfiles[i].system.modules.length; x++) {
							switch (data.systemProfiles[i].system.modules[x].module.id) {
								
								case 4:
									$scope.companyMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 5:
									$scope.hardwareMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 7:
									$scope.hardwareModelMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 9:
									$scope.invoiceMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;	
								
								case 12:
									$scope.managementMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 13:
									$scope.kitMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 15:
									$scope.kitMapsSmeMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 16:
									$scope.generalReportMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 18:
									$scope.historicReportMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 19:
									$scope.minimumStockReportMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 20:
									$scope.kitReportMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 21:
									$scope.EquipmentUnmanagedReportMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 22:
									$scope.KitUnmanagedReportMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 23:
									$scope.kitMapsMicroserversMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 24:
									$scope.criticalityMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 26:
									$scope.incidentMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
								
								case 27:
									$scope.financialReportMenu = data.systemProfiles[i].system.modules[x].permission.r;
								break;
									
								case 33:
									$scope.generalExcelReport = data.systemProfiles[i].system.modules[x].permission.r;
								break;
							}
								
						}
						if ($scope.companyMenu || $scope.hardwareMenu || $scope.hardwareModelMenu) {
							$scope.registerMenu = true;
						}
						
						if ($scope.generalReportMenu || $scope.KitUnmanagedReportMenu || $scope.EquipmentUnmanagedReportMenu || $scope.historicReportMenu || $scope.minimumStockReportMenu || $scope.kitReportMenu || $scope.financialReportMenu) {
							$scope.reportMenu = true;
						}
					}
				}
				
				$rootScope.$broadcast('session', data);
			} else {
				location.href="/cps/";
			}
		}).error(function (data, status){
			location.href="/cps/";
		});
	}
	
	$scope.getSessionLocal();
	
	$scope.logoff = function(){
		$http.get('/cps/rest/logoff').success(function(data){
			location.href="/cps/";
		});
	};
	
}]);



function changeCSS(pSystem, pColor) {
	var css = null;
	
	switch (pColor) {
	case "green":
		css = pSystem + "/css/styleGreen.css";
		break;
	case "pink":
		css = pSystem + "/css/stylePink.css";
		break;
	case "black":
		css = pSystem + "/css/styleBlack.css";
		break;
	case "gray":
		css = pSystem + "/css/styleGray.css";
		break;
	case "white":
		css = pSystem + "/css/styleWhite.css";
		break;
	default:
		css = pSystem + "/css/styleBlue.css";
	}

	generateCookie("background", pColor, 365);
	window.document.getElementById('styleAll').setAttribute('href', css);
	return true;
}

// Funcao para criar o cookie.
// Para que o cookie seja destruido quando o browser for fechado, basta passar 0
// no parametro lngDias.
function generateCookie(strCookie, strValor, lngDias) {

	var dtmData = new Date();
	var strExpires;

	if (lngDias) {
		dtmData.setTime(dtmData.getTime() + (lngDias * 24 * 60 * 60 * 1000));
		strExpires = "; expires=" + dtmData.toGMTString();
	} else {
		strExpires = "";
	}
	document.cookie = strCookie + "=" + strValor + strExpires + "; path=/";

	return true;
}

// Funcao para ler o cookie.
function getCookie(strCookie) {
	var strNomeIgual = strCookie + "=";
	var arrCookies = document.cookie.split(';');

	for (var i = 0; i < arrCookies.length; i++) {
		var strValorCookie = arrCookies[i];
		while (strValorCookie.charAt(0) == ' ') {
			strValorCookie = strValorCookie.substring(1, strValorCookie.length);
		}
		if (strValorCookie.indexOf(strNomeIgual) == 0) {
			return strValorCookie.substring(strNomeIgual.length,
					strValorCookie.length);
		}
	}
	return null;
}

// Funcao para excluir o cookie desejado.
function deleteCookie(strCookie) {
	generateCookie(strCookie, '', -1);
	return true;
}

function gerateTemplate(pSystem) {
	var background = getCookie("background");
	if (background != null) {
		changeCSS(pSystem, background);
	} else {
		changeCSS(pSystem, 'blue');
	}
	
	return true;
}

function todayDate(vLinguage) {
	
	var hj = new Date();
	var strExtenso = "";
	var semana = new Array(6);
	var mes = new Array(12);

	// Day of Week
	if (vLinguage == 'ing') {
		semana[0] = "Sunday";
		semana[1] = "Monday";
		semana[2] = "Tuesday";
		semana[3] = "Wednesday";
		semana[4] = "Thursday";
		semana[5] = "Friday";
		semana[6] = "Saturday";
	}
	if (vLinguage == 'pt') {
		semana[0] = "Domingo";
		semana[1] = "Segunda-Feira";
		semana[2] = "Ter&ccedil;a-Feira";
		semana[3] = "Quarta-Feira";
		semana[4] = "Quinta-Feira";
		semana[5] = "Sexta-Feira";
		semana[6] = "Sab&aacute;do";
	}
	// Month and Year
	if (vLinguage == 'pt') {
		mes[0] = "Janeiro";
		mes[1] = "Fevereiro";
		mes[2] = "Mar&ccedil;o";
		mes[3] = "Abril";
		mes[4] = "Maio";
		mes[5] = "Junho";
		mes[6] = "Julho";
		mes[7] = "Agosto";
		mes[8] = "Setembro";
		mes[9] = "Outubro";
		mes[10] = "Novembro";
		mes[11] = "Dezembro";
	}
	if (vLinguage == 'ing') {
		mes[0] = "January";
		mes[1] = "February";
		mes[2] = "March";
		mes[3] = "April";
		mes[4] = "May";
		mes[5] = "June";
		mes[6] = "Jully";
		mes[7] = "August";
		mes[8] = "September";
		mes[9] = "October";
		mes[10] = "November";
		mes[11] = "December";
	}
	if (vLinguage == 'pt') {
		strExtenso = semana[hj.getDay()] + " dia " + hj.getDate() + " de "
				+ mes[hj.getMonth()] + " de " + hj.getFullYear();
	}
	if (vLinguage == 'ing') {
		strExtenso = semana[hj.getDay()] + " " + hj.getDate() + " "
				+ mes[hj.getMonth()] + " of " + hj.getFullYear();
	}
	return strExtenso;
}
function cumprimento(vLinguagem) {
	var day = new Date();
	var hr = day.getHours();
	var strInfo = "";

	if ((hr >= 5) && (hr <= 11)) {
		if (vLinguagem == "pt")
			strInfo = "Bom dia!";
		if (vLinguagem == "ing")
			strInfo = "Good Morning ";
		return strInfo;
	}
	if ((hr >= 12) && (hr <= 17)) {
		if (vLinguagem == "pt")
			strInfo = "Boa tarde!";
		if (vLinguagem == "ing")
			strInfo = "Good Afternoon ";
		return strInfo;
	}
	if ((hr >= 18) && (hr <= 24) || (hr >= 0) && (hr <= 5)) {
		if (vLinguagem == "pt")
			strInfo = "Boa noite!";
		if (vLinguagem == "ing")
			strInfo = "Good Evening ";
		return strInfo;
	}

	return null;
}

function generateTopTodayDate() {
	document.getElementById("TopTodayDate").innerHTML = todayDate("ing") + ", "
			+ cumprimento("ing");
	return true;
}

$(document).ready(function() {
	generateTopTodayDate();
});

function getParameter(url, name){
	if (url.indexOf("?") > 0) {
		query = url.split("?"); param = query[1].split("&");
		for (i=0; i < param.length; i++) { 
			v = param[i].split("=");
			if (name == v[0]){
				return v[1];
			}
		} 
	}
	return null;
}

function subtractDate(date, day){
    return new Date(date.getTime() - (day * 24 * 60 * 60 * 1000));
 }
