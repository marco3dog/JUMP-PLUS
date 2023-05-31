const dbApi = { 

    baseURL: "http://localhost:3001",

    getAllUsers: async () => {
        const URI = dbApi.baseURL + "/users";
        let users = [];
        
        await fetch(URI)
        .then((response) => response.json())
        .then((json) => users = json)
        .catch((e) => console.log(e));
        
        return users;
    },
    
    
    login: async (username, password) => {
        const users = await dbApi.getAllUsers();

        for (let i = 0; i < users.length; i++) {
            if (username === users[i].username && password === users[i].password) {
                return users[i];
            }
        }
        return undefined;
    },

    putUser: async (user) => {
        const URI = dbApi.baseURL + "/users/" + user.id;

        const putFormat = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        };

        await fetch(URI, putFormat);
    }, 
    
    getAllItems: async () => {
        let items = [];
        const URI = dbApi.baseURL + "/items";

        await fetch(URI)
        .then((response) => response.json())
        .then((json) => items = json)
        .catch((e) => console.log(e));
        
        return items;
    },

    getItemById: async (itemId) => {
        let items = await dbApi.getAllItems();
        for(let i = 0; i < items.length; i++){
            if(itemId == items[i].id){
                return items[i];
            }
        }
        return undefined;
    },

    getAllOrders: async () => {
        let orders = [];
        const URI = dbApi.baseURL + "/orders";

        await fetch(URI)
        .then((response) => response.json())
        .then((json) => orders = json)
        .catch((e) => console.log(e));
        
        return orders;
    },

    getOrdersByCustomer: async (customerId) => {
        const orders = await dbApi.getAllOrders();
        let customerOrders = [];

        for (let i = 0; i < orders.length; i++) {
            if (customerId == orders[i].userId ) {
                customerOrders.push(orders[i]);
            }
        }
        return customerOrders;
    },

    postUser: async (usr) => {
        const users = await dbApi.getAllUsers();

        for (let i = 0; i < users.length; i++) {
            if (usr.username == users[i].username){
                return false;
            }
        }

        usr.id = users.length + 1;

        const URI = dbApi.baseURL + "/users";
        const postFormat = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(usr)
        };

        await fetch(URI, postFormat);
        return true;
    },

    postOrder: async (order) => {
        const orders = await dbApi.getAllOrders();
        order.id = orders.length + 1;
        const URI = dbApi.baseURL + "/orders";
        const postFormat = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(order)
        };
        await fetch(URI, postFormat);
        return true;
    }
}

module.exports = {
    dbApi
}