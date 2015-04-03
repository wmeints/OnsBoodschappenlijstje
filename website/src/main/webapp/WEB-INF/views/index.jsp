<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Boodschappenlijstje</title>
    <link rel="stylesheet" type="text/css" href="app/bower_components/angular-material/angular-material.min.css"/>
    <link rel="stylesheet" type="text/css" href="app/bower_components/angular-ui/build/angular-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="app/styles/sprite-action-black.css" />
    <link rel="stylesheet" type="text/css" href="app/styles/app.css"/>
</head>
<body ng-app="boodschappenlijstje" layout="column" ng-controller="MainCtrl as vm">
    <md-toolbar>
        <h2 class="md-toolbar-tools">
            <span>Boodschappen</span>
        </h2>
    </md-toolbar>
    <md-content>
        <div flex layout="row" class="item-input-form">
            <md-input-container flex>
                <label>Item</label>
                <input type="text" ui-keyup="{ 'enter': 'vm.saveItem()'}"  ng-model="vm.newItemText"/>
            </md-input-container>
            <md-button class="md-primary" ng-click="vm.saveItem()">
                <div class="icon-action-black icon-action-black-ic_shopping_cart_black_24dp"></div>
            </md-button>
        </div>
        <section>
            <md-subheader class="md-primary">Openstaande items</md-subheader>
            <md-item ng-repeat="item in vm.items | filter: { done: false }" class="todo-item">
                <md-item-content layout="row">
                    <md-checkbox ng-checked="vm.isSelected(item)" ng-click="vm.updateItemStatus(item)" flex>
                        {{item.item}} <span class="subtle" ng-show="item.winkel">@{{item.winkel}}</span>
                    </md-checkbox>
                    <md-button ng-click="vm.editItem(item)">
                        <div class="icon-action-black icon-action-black-ic_settings_black_24dp"></div>
                    </md-button>
                    <md-button ng-click="vm.removeItem(item)">
                        <div class="icon-action-black icon-action-black-ic_delete_black_24dp"></div>
                    </md-button>
                </md-item-content>
            </md-item>
        </section>
        <section>
            <md-subheader class="md-primary">Voltooide items</md-subheader>
            <md-item ng-repeat="item in vm.items | filter: { done: true }" class="completed-todo-item todo-item">
                <md-item-content layout="row">
                    <md-checkbox ng-checked="vm.isSelected(item)" ng-click="vm.updateItemStatus(item)" flex>
                        {{item.item}} <span class="subtle" ng-show="item.winkel">@{{item.winkel}}</span>
                    </md-checkbox>
                    <md-button ng-click="vm.editItem(item)">
                        <div class="icon-action-black icon-action-black-ic_settings_black_24dp"></div>
                    </md-button>
                    <md-button ng-click="vm.removeItem(item)">
                        <div class="icon-action-black icon-action-black-ic_delete_black_24dp"></div>
                    </md-button>
                </md-item-content>
            </md-item>
        </section>
    </md-content>

    <script type="text/javascript" src="app/bower_components/angular/angular.min.js"></script>
    <script type="text/javascript" src="app/bower_components/angular-animate/angular-animate.min.js"></script>
    <script type="text/javascript" src="app/bower_components/angular-aria/angular-aria.min.js"></script>
    <script type="text/javascript" src="app/bower_components/angular-material/angular-material.min.js"></script>
    <script type="text/javascript" src="app/bower_components/angular-ui/build/angular-ui.min.js"></script>
    <script type="text/javascript" src="app/scripts/app.js"></script>
    <script type="text/javascript" src="app/scripts/service/boodschappen.js"></script>
    <script type="text/javascript" src="app/scripts/controller/main.js"></script>
</body>
</html>
