var app = angular.module('app', ['ui.grid', 'ui.grid.selection', 'ui.grid.resizeColumns']);
     
app.controller('MainCtrl', ['$scope', 'uiGridConstants', function ($scope, uiGridConstants) {
    	
    $scope.gridOptions = { 
    		enableRowSelection: true, 
    		enableRowHeaderSelection: false, 
    		enableFiltering: false, 
    		rowHeight: 25
    };
    console.log($scope.gridOptions)
    $scope.gridOptions.multiSelect = false;
    $scope.gridOptions.modifierKeysToMultiSelect = false;
    $scope.gridOptions.noUnselect = false;
    $scope.gridOptions.onRegisterApi = function( gridApi ) {
	$scope.gridApi = gridApi;
	};
	
	$scope.barButton = false;
	
	$scope.toggleRowSelection = function() {
		 $scope.gridApi.selection.clearSelectedRows();
		 $scope.gridOptions.enableRowSelection = !$scope.gridOptions.enableRowSelection;
		 $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.OPTIONS);
	};
		 
	$scope.gridOptions.onRegisterApi = function(gridApi){
		//set gridApi on scope
		$scope.gridApi = gridApi;
		gridApi.selection.on.rowSelectionChanged($scope,function(row){
			var msg = 'row selected ' + row.isSelected;
			if (row.isSelected) {
				$scope.barButton = true;
			} else{
				$scope.barButton = false;
			}
			console.log(msg);
			console.log(row.entity)
		});
	};
		 
	$scope.gridOptions.columnDefs = [
		{ name: 'firstName', width: '50%'},
		{ name: 'lastName'},
		{ name: 'company' },
		{ name: 'status',
			cellClass: 'grid_status',
			cellTemplate: '<i class="{{(row.entity[col.field]?\'fa fa-check-circle enabled\':\'fa fa-minus-circle disabled\')}}"></i>'
		}
	];
		 
	$scope.gridOptions.data = [
	    {
	    "firstName": "Cox",
	    "lastName": "Carney",
	    "company": "Enormo",
	    "status": true
	    },
	    {
	    "firstName": "Lorraine",
	    "lastName": "Wise",
	    "company": "Comveyer",
	    "status": false
	    },
	    {
	    "firstName": "Nancy",
	    "lastName": "Waters",
	    "company": "Fuelton",
	    "status": false
	    }
    ];
	
	$scope.add = function(){
		console.log("Add new");
	}
	
	$scope.edit = function(){
		console.log("Edit");
		var list = $scope.gridApi.selection.getSelectedGridRows();
		console.log(list[0].entity);
	}
	
	$scope.view = function(){
		console.log("View");
		var list = $scope.gridApi.selection.getSelectedGridRows();
		console.log(list[0].entity);
	}
	
	$scope.remove = function(){
		console.log("Delete");
		var list = $scope.gridApi.selection.getSelectedGridRows();
		console.log(list[0].entity);
	}
	
}]);