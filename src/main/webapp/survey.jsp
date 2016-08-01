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
				<span>Todos já sabem que falar inglês é muito importante, seja para uma viagem, ou para uma dão sonhada promoção no trabalho.</span><br/>
				<span>Mais arrumar tempo para ir em um escola tradicional, com metodos tradicionais, que te levara a fluência em aproximadamente 5 anos ou mais.</span><br/>
				<span>Não se encaixa mais na vida cotidiana que vivems, onde o espaço para adquirir conhecimento é curto.</span><br/>
				<span>Após muitos anos de estudo e cansado de gastar fortunas em escolas tradicionais, descobri uma formula de como aprender não so o idioma ingles.</span><br/>
				<span>Mais qualquer idioma do mundo em um menor tempo do que os metodos convencionais.</span>
		
			</div>
			<span>Pesquisa comprovam.</span>
			<div>
				
				<img src="${pageContext.request.contextPath}/imgs/structure/mundo2.png" class="img-responsive">
				<img src="${pageContext.request.contextPath}/imgs/structure/mundo.jpg" class="img-responsive">
			</div>
			
			<form name="form" ><p>
				<span>Responda nossa pesquisa e ganhe um modulo inicial do nosso curso no dia do lançamento, que esta previsto para a primeira quinzena de dezembro de 2016.</span>
				<div class="form-group">
				    <span >Nome</span>
					<input type="text" name="name" class="form-control" ng-model="user.name" required><p></p>
				</div>
				<div class="form-group">
				    <span >Oque você espera do nosso curso?</span>
					<input type="text" name="question1" class="form-control" ng-model="survey.question1" required><p></p>
				</div>
				<div class="form-group">
				    <span >Quanto você esta disposto a pagar para ser fluênte em inglês?</span>
					<input type="text" name="question2" class="form-control" ng-model="survey.question2" required><p></p>
				</div>
				<div class="form-group">
				    <span >O que você realmente precisa?</span>
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