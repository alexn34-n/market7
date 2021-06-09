angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';


    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                'name': $scope.filter ? $scope.filter.name : null,
                'min_price': $scope.filter ? $scope.filter.min_price : null,
                'max_price': $scope.filter ? $scope.filter.max_price : null,
                pageNumber: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage= response.data;

            let minPageIndex = pageIndex - 1;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        });
    };

    $scope.showCart = function () {
        $http({
            url:contextPath+'/cart',
            method: 'GET'
        }).then(function (response){
                $scope.Cart=response.data;
            })
    };

    $scope.generatePagesIndexes = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }


    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.NewProduct)
            .then(function (response){
                $scope.NewProduct = null
                $scope.fillTable();
            });

    };

    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response){
                $scope.fillTable();
            });
    }

    $scope.addToCart = function (productId) {
        $http.put(contextPath + '/cart?productId=' + productId)
            .then(function (response){
                $scope.showCart();
            });
    }

    $scope.clearCart = function (productId) {
        $http.deletr(contextPath + '/cart')
            .then(function (response){
                $scope.showCart();
            });
    }

    $scope.fillTable();
    $scope.showCart();
});