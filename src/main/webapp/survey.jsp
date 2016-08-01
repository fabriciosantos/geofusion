<!DOCTYPE html>
<html lang="pt" ng-app="Main" >
	<head>
		<title>English</title>
		<base href="/">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/libs/bootstrap/dist/css/bootstrap.css">
	    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/angular/angular.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/controller/controllerSurvey.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/angular/angular-route.min.js"></script>
					
	</head>
	<body class="local" ng-controller="TodoControllerSurvey">
		<header>
			<div>
				<span>O mundo realmente entende oque voce fala</span><br/>
			</div>
		</header>
		<div class="container">
			<div>
				<span>Todos j� sabem que falar ingl�s � muito importante, seja para uma viagem, ou para uma d�o sonhada promo��o no trabalho.</span><br/>
				<span>Mais arrumar tempo para ir em um escola tradicional, com metodos tradicionais, que te levara a flu�ncia em aproximadamente 5 anos ou mais.</span><br/>
				<span>N�o se encaixa mais na vida cotidiana que vivems, onde o espa�o para adquirir conhecimento � curto.</span><br/>
				<span>Ap�s muitos anos de estudo e cansado de gastar fortunas em escolas tradicionais, descobri uma formula de como aprender n�o so o idioma ingles.</span><br/>
				<span>Mais qualquer idioma do mundo em um menor tempo do que os metodos convencionais.</span>
		
			</div>
			<span>Pesquisa comprovam.</span>
			<div>
				
				<img src="${pageContext.request.contextPath}/imgs/structure/mundo2.png" class="img-responsive">
				<img src="${pageContext.request.contextPath}/imgs/structure/mundo.jpg" class="img-responsive">
			</div>
			
			<form name="form" ><p>
				<span>Responda nossa pesquisa e ganhe um modulo inicial do nosso curso no dia do lan�amento, que esta previsto para a primeira quinzena de dezembro de 2016.</span>
				<div class="form-group">
				    <span >Nome</span>
					<input type="text" name="name" class="form-control" ng-model="user.name" required><p></p>
				</div>
				<div class="form-group">
				    <span >Oque voc� espera do nosso curso?</span>
					<input type="text" name="question1" class="form-control" ng-model="survey.question1" required><p></p>
				</div>
				<div class="form-group">
				    <span >Quanto voc� esta disposto a pagar para ser flu�nte em ingl�s?</span>
					<input type="text" name="question2" class="form-control" ng-model="survey.question2" required><p></p>
				</div>
				<div class="form-group">
				    <span >O que voc� realmente precisa?</span>
					<input type="text" name="question3" class="form-control" ng-model="survey.question3" required><p></p>
				</div>
				<div class="form-group">
			  	<button type="button" save()" class="btn btn-primary" Data-ng-disabled="form.$invalid">Enviar</button>
				</div>
				</div>
			</form>
		</div>
	</body>
</html>