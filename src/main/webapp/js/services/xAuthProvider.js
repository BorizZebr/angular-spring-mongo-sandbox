/**
 * Created by borisbondarenko on 22.12.15.
 */
(function(){

    angular.module('pinmap')
        .service('xAuthProvider', ['$http', 'localStorageService', xAuthProvider]);

    function xAuthProvider($http, localStorageService){

        return {
            login: login,
            logout: logout,
            getToken: getToken,
            hasValidToken: hasValidToken
        };

        function login(credentials){
            var data = "username=" +  encodeURIComponent(credentials.username) + "&password="
                + encodeURIComponent(credentials.password);
            return $http.post('api/authenticate', data, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "Accept": "application/json"
                }
            }).success(function (response) {
                localStorageService.set('token', response);
                return response;
            });
        }

        function logout() {
            localStorageService.clearAll();
        }

        function getToken(){
            return localStorageService.get('token');
        }

        function hasValidToken(){
            var token = this.getToken();
            return token && token.expires && token.expires > new Date().getTime();
        }
    }
})();