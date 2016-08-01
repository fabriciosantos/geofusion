<!DOCTYPE html>
<html lang="pt" ng-app="App" >
	<head>
		<title>English</title>
		<base href="/">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<link rel="stylesheet" type="text/css" href="assets/libs/bootstrap/dist/css/bootstrap.css">
	    <script src="../scripts/angular/angular.js"></script>
		<script src="../scripts/jquery/jquery.min.js"></script>
		<script src="scripts/controller.js"></script>
					
	</head>
	<body class="local" ng-controller="TodoController">
		<header>
			<div>
				<span>O mundo realmente entende oque voce fala</span><br/>
			</div>
		</header>
		<div class="container">
			<div>
				<span>Pesquisas comprovam que a cada 5 pessoas no mundo 1 fala ingl�s.</span><br/>
				<span>Sal�rio � at� 61% maior entre quem fala ingl�s fluente para quem n�o fala.</span><br/>
				<span>Te ofere�o a verdade que as escolas tradicionais te escondem, sim � possivel ficar fluente na lingua inglesa em 4 meses.</span><br/>
				<span>Cadastre seu email para receber em primeira m�o este curso que vai revolucionar o jeito de aprender ingl�s.</span><br/>
			</div>
			<form name="form" ><p>
				<div class="form-group">
			  	<input type="email" name="email" class="form-control" ng-model="user.email" placeholder="Email" required>
				<button type="button" save()" class="btn btn-primary" Data-ng-disabled="form.$invalid">Enviar</button>
				</div>
				</div>
			</form>							
		</div>
	</body>
</html>