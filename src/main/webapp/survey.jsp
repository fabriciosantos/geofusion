<!DOCTYPE html>
<html lang="pt" ng-app="Main" >
	<head>
		<title>Flu�ncia Agora</title>
		<base href="/">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/angular/angular.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/controllers/controllerSurvey.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/angular/angular-route.min.js"></script>
					
	</head>
	<body class="local" ng-controller="TodoControllerSurvey">
		<header>
			<div class="header-content">
            <div class="header-content-inner">
                
				<h1>Seja Bem Vindo</h1>
				<h2>E j� deixa eu te parabenizar pela excelente decis�o.</h2>
			</div>
			</div>
		</header>
		<div class="container">
			<div>
				</br><h2>Todos j� sabem que falar ingl�s � muito importante, seja para uma viagem, ou para uma d�o sonhada promo��o no trabalho.
				Mais arrumar tempo para ir em um escola tradicional, com metodos tradicionais, que te levara a flu�ncia em aproximadamente 5 anos ou mais.
				N�o se encaixa mais na vida cotidiana que vivems, onde o espa�o para adquirir conhecimento � curto.</h2>
				<h2>Ap�s muitos anos de estudo e cansado de gastar fortunas em escolas tradicionais, descobri uma formula de como aprender n�o so o idioma ingles.
				Mais qualquer idioma do mundo em um menor tempo do que os metodos convencionais.</h2>
		
			</div>
			<div>			
				<img src="${pageContext.request.contextPath}/imgs/structure/mundo2.png" width="500" class="img-rounded">
				<img src="${pageContext.request.contextPath}/imgs/structure/mundo.jpg" width="500" class="img-rounded">
			</div>
			
			<form name="form" ><p>
				<h2>Responda nossa pesquisa e ganhe um modulo inicial do nosso curso no dia do lan�amento, que esta previsto para a primeira quinzena de dezembro de 2016.</h2></br>
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