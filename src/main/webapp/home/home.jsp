<!DOCTYPE html>
<html ng-app="app">
<head>
	<title>Portal</title>
	<meta charset="utf-8">
	<%@include file="../structure/link.html" %>
	<script type="text/javascript" src="scripts/controller.js"></script>
</head>
<body ng-controller="todoControllerHome">

	<!-- begin general content -->
	<div class="container-fluid area">
	
		<!-- begin header -->
		<%@include file="../structure/header.html" %>
		<!--end header -->
		
		<!-- begin body -->
		<div class="row" >
		
			<!-- panel body -->
			<div class="col-md-12 content">
				<div class="panel panel-default">
				  <div class="panel-heading background_title">Minimum Stock</div>
					<div class="panel-body">
						<div class="container-fluid">
							<div class="row" style="border: 1px solid #CCCCCC; overflow: hidden">
								<div class="barGrid" ng-show="barButton">
									<span ng-show="barEdit" class="barButton" ng-click="onEdit()"><i class="fa fa-pencil iconBarButton"></i> Edit</span>
									<span class="barButton" ng-click="onView()"><i class="fa fa-file iconBarButton"></i> View</span>
									<span ng-show="barDelete" class="barButton" ng-click="onRemove()"><i class="fa fa-trash iconBarButton"></i> Delete</span>
								</div>
								<div class="boxGrid" id="grid" ui-grid="gridOptions " ui-grid-selection ui-grid-resize-columns class="grid"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
				
		</div>
		<!-- end body -->
		
		<!-- begin footer -->
		<%@include file="../structure/footer.html" %>
		<!-- end footer -->
		
	</div>
	<!-- end general content -->

</body>
</html>