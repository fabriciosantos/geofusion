<!DOCTYPE html>
<html lang="pt" ng-app="Main" >
	<head>
		<title>English</title>
		<base href="/">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/angular/angular.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/controllers/controllerUser.js"></script>
					
	</head>
	<body class="local" ng-controller="TodoControllerUser">
		<header>
			<div>
				<span>O mundo realmente entende oque voce fala</span><br/>
			</div>
		</header>
		<div class="container">
			<div>
				<span>Pesquisas comprovam que a cada 5 pessoas no mundo 1 fala inglês.</span><br/>
				<span>Salário é até 61% maior entre quem fala inglês fluente para quem não fala.</span><br/>
				<span>Te ofereço a verdade que as escolas tradicionais te escondem, sim é possivel ficar fluente na lingua inglesa em 4 meses.</span><br/>
				<span>Cadastre seu email para receber em primeira mão este curso que vai revolucionar o jeito de aprender inglês.</span><br/>
			</div>
			<form name="form" ><p>
				<div class="form-group">
			  	<input type="email" name="email" class="form-control" ng-model="user.email" placeholder="Email" required><p><p>
				<button type="button" Data-ng-click="save()" class="btn btn-primary" Data-ng-disabled="form.$invalid">Enviar</button>
				</div>
				</div>
			</form>							
		</div>
	</body>
</html>