/*!
 * Start Bootstrap - SB Admin 2 v3.3.7+1 (http://startbootstrap.com/template-overviews/sb-admin-2)
 * Copyright 2013-2016 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap/blob/gh-pages/LICENSE)
 */
$(function() {
    $('#side-menu').metisMenu();
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        var topOffset = 50;
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    // var element = $('ul.nav a').filter(function() {
    //     return this.href == url;
    // }).addClass('active').parent().parent().addClass('in').parent();
    var element = $('ul.nav a').filter(function() {
        return this.href == url;
    }).addClass('active').parent();

    while (true) {
        if (element.is('li')) {
            element = element.parent().addClass('in').parent();
        } else {
            break;
        }
    }
});

/*
var isBusiness = false;
$(document).ready(function() {
    $('#biz').click(function() {
        if(isFinance){
            isFinance = false;
            isBusiness = true;
        }else{
            isBusiness = !isBusiness;
            $('#loginFormDiv').slideToggle("slow");
        }

        if(isBusiness){
            isFinance = false;
            $("#biz").css("background-color", "#555");
            $("#biz").css("border-radius", "10px");
            $("#biz").css("color", "white");
            $("#fin").css("color", "#337ab7");
            $("#fin").css("background-color", "white");
        }else{
            $("#biz").css("color", "#337ab7");
            $("#biz").css("background-color", "white");
        }
        
    });
});

var isFinance = false;
$(document).ready(function() {
    $('#fin').click(function() {
        if(isBusiness){
            isBusiness = false;
            $("#biz").css("color", "#337ab7");
            $("#biz").css("background-color", "white");
            isFinance = true;
        }else{
            isFinance = !isFinance;
            $('#loginFormDiv').slideToggle("slow");
        }
        if(isFinance){
            isBusiness = false;
            $("#fin").css("background-color", "#555");
            $("#fin").css("border-radius", "10px");
            $("#fin").css("color", "white");
            $("#biz").css("color", "#337ab7");
        }else{
            $("#fin").css("color", "#337ab7");
            $("#fin").css("background-color", "white");
        }
    });
});
*/

var app = angular.module("SignupSwitch", []);
app.controller("mySignupSwitch", ["$scope", function($scope) {
    $scope.bizvar = false;
    $scope.finvar = false;
    $scope.nopage = true;

    $scope.hid = "hidden"
    $scope.nhid = ""
    $scope.curstylebiz = "bizfintrue";
    $scope.curstylefin = "finbiztrue";
    $scope.myurl = "";
    $scope.collapse = "";
    $scope.bizClick = function() {
        $scope.myurl ="./SignupCustomer.html"
        if ($scope.bizvar == false) {
            if ($scope.finvar == true) {
                $scope.collapse = "";
            } else {
                $scope.collapse = "collapse";
            }
            
            $scope.bizvar = true;
            $scope.finvar = false;
            $scope.nopage = false;
            $scope.curstylebiz = "bizbiztrue";
            $scope.curstylefin = "finbiztrue";
        } else if ($scope.bizvar == true){
            $scope.collapse = "collapse";
            $scope.bizvar = false;
            $scope.curstylebiz = "bizfintrue";
            $scope.curstylefin = "finbiztrue";
            if ($scope.finvar == false) {
                $scope.nopage = true;
            }
        }
        console.log($scope.bizvar);
        console.log($scope.finvar);
        console.log($scope.nopage);
    }

    $scope.finClick= function() {
        if ($scope.finvar == false) {
            if ($scope.bizvar == true) {
                $scope.collapse = "";
            } else {
                $scope.collapse = "collapse";
            }
            $scope.myurl ="./SignupUser.htm"
            $scope.curstylefin = "finfintrue";
            $scope.curstylebiz = "bizfintrue";
            $scope.bizvar = false;
            $scope.finvar = true;
            $scope.nopage = false;
        } else if ($scope.finvar == true){
            $scope.collapse = "collapse";
            $scope.curstylefin = "finbiztrue";
            $scope.finvar = false;
            if ($scope.bizvar == false) {
                $scope.nopage = true;
            }
        }
        console.log($scope.bizvar);
        console.log($scope.finvar);
        console.log($scope.nopage);
      

    }
}]);


 
app.controller("LoginController", ["$scope", "$http", "$httpParamSerializer", "$window", function($scope, $http, $httpParamSerializer, $window) {

    $scope.UserNameMod = "";
    $scope.PasswordMod = "";
    $scope.LoginClick = function () {
       var data = {
            username: $scope.UserNameMod,
            password: $scope.PasswordMod
        }

        var promise = $http({
            url: 'http://localhost:8089/MyRestDemo/rest/login/checkCredentials',
            method: 'POST',
            headers: { 'Content-Type': 'text/plain' },
            data: {
            username: $scope.UserNameMod,
            password: $scope.PasswordMod
            }
        })
        promise.then(function(){
            //$location.url('./Dashboard.html')
        	$window.location.href = './Dashboard.html'
        })
    }

}]);



app.controller("TestController", ["$scope", "$http", function($scope, $http) {

    $scope.UserNameMod = "";
    $scope.PasswordMod = "";
    $scope.TestClick = function () {
       

        var promise = $http({
            url: 'http://localhost:8089/MyRestDemo/rest/login/getUserInfo',
            method: 'POST',
            headers: { 'Content-Type': 'text/plain' }
        })
        
    }

}]);


var Signupapp = angular.module("SignupApp", []);

Signupapp.controller("SignupCustController", ["$scope", "$http", "$httpParamSerializer", "$window", function($scope, $http, $httpParamSerializer, $window){
		
	$scope.UserNameMod = "";
    $scope.EmailMod = "";
    $scope.Password = "";
    $scope.RePassword = "";
    
    $scope.SubmitSignup = function () {
       var data = {
            username: $scope.UserNameMod,
            email:$scope.EmailMod,
            password: $scope.Password
        }
       console.log(data);
        var promise = $http({
            url: 'http://localhost:8089/MyRestDemo/rest/login/signupCustomer/',
            method: 'POST',
            headers: { 'Content-Type': 'text/plain' },
            data: {
            	username: $scope.UserNameMod,
                email:$scope.EmailMod,
                password: $scope.Password
            }
        });
        promise.then(function(response){
            //$location.url('./Dashboard.html')
        	console.log(response);
        	$window.location.href = './HomePage.html'
        });
    }
	
}]);
















