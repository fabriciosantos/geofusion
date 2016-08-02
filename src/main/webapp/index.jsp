<!DOCTYPE html>
<html lang="pt" ng-app="Main" >
	<head>
		<title>Fluência Agora</title>
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
        <div class="header-content">
            <div class="header-content-inner">
                <h1 id="homeHeading">O mundo entende oque você fala?</h1><p></p>
                
                <span>Pesquisas comprovam que a cada 5 pessoas no mundo 1 fala inglês.</span>
                <span>Salário é até 61% maior entre quem fala inglês fluente para quem não fala.</span><br/>
				<span>Te ofereço a verdade que as escolas tradicionais te escondem, sim é possivel ficar fluente na lingua inglesa em 4 meses.</span><br/>
				<span>Cadastre seu email para receber em primeira mão este curso que vai revolucionar o jeito de aprender inglês.</span><br/>
		        <form name="form" ><p>
				<div class="form-group">
			  	<input type="email" name="email" class="form-control" Data-ng-model="user.email" placeholder="Email" required><p><p>
			    <a class="btn btn-primary btn-xl page-scroll" Data-ng-click="save()" Data-ng-disabled="form.$invalid">Enviar</a>
    	</div>
				</div>
			</form>							
	
            </div>
        </div>
    </header>
		
	</body>
</html>