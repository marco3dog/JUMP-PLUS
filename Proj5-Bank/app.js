let user1 = {
    username: "user1",
    password: "pwd1",
    email: "user1@email.com",
    phone: "(111) 111-1111",
    accounts: [
        {
            accountNumber: 101,
            balance: 5000,
            transactions: [
                {
                    type: "Deposit",
                    amount: 1000,
                    after_balance: 1000
                },
                {
                    type: "Deposit",
                    amount: 1000,
                    after_balance: 2000
                },
                {
                    type: "Deposit",
                    amount: 1000,
                    after_balance: 3000
                },
                {
                    type: "Deposit",
                    amount: 1000,
                    after_balance: 4000
                },
                {
                    type: "Deposit",
                    amount: 1000,
                    after_balance: 5000
                }
            ]
        },
        {
            accountNumber: 102,
            balance: 15000,
            transactions: [
                {
                    type: "Deposit",
                    amount: 3000,
                    after_balance: 3000
                },
                {
                    type: "Deposit",
                    amount: 3000,
                    after_balance: 6000
                },
                {
                    type: "Deposit",
                    amount: 3000,
                    after_balance: 9000
                },
                {
                    type: "Deposit",
                    amount: 3000,
                    after_balance: 12000
                },
                {
                    type: "Deposit",
                    amount: 3000,
                    after_balance: 15000
                }
            ] 
        }
    ]
};

let user2 = {
    username: "user2",
    password: "pwd2",
    email: "user2@email.com",
    phone: "(222) 222-2222",
    accounts: [
        {
            accountNumber: 201,
            balance: 1000,
            transactions: [
                {
                    type: "Deposit",
                    amount: 5000,
                    after_balance: 5000
                },
                {
                    type: "Withdraw",
                    amount: 1000,
                    after_balance: 4000
                },
                {
                    type: "Withdraw",
                    amount: 1000,
                    after_balance: 3000
                },
                {
                    type: "Withdraw",
                    amount: 1000,
                    after_balance: 2000
                },
                {
                    type: "Withdraw",
                    amount: 1000,
                    after_balance: 1000
                }
            ]
        },
        {
            accountNumber: 202,
            balance: 25000, 
            transactions: [
                {
                    type: "Deposit",
                    amount: 10000,
                    after_balance: 10000
                },
                {
                    type: "Deposit",
                    amount: 5000,
                    after_balance: 15000
                },
                {
                    type: "Deposit",
                    amount: 5000,
                    after_balance: 20000
                },
                {
                    type: "Deposit",
                    amount: 10000,
                    after_balance: 30000
                },
                {
                    type: "Withdraw",
                    amount: 5000,
                    after_balance: 25000
                }
            ]
        }
    ]
};

let users = [user1, user2];

var currentUser = null;


function validate() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    for(let i=0;i<users.length;i++){
        if(username === users[i].username && password === users[i].password){
            currentUser = users[i];
            localStorage.setItem('CU', JSON.stringify(currentUser));
            console.log("Success");
            return true;
        }
    }

    console.log("Invalid Credentials");
    return false;
}

function generateLastFiveTransactions(){
    
    let tr = "";
    currentUser = JSON.parse(localStorage.getItem('CU'));
    for(let i = 0; i < currentUser.accounts.length; i++){
        let n = currentUser.accounts[i].transactions.length;
        tr +=
        `<h3>Account Number: ${currentUser.accounts[i].accountNumber}   Balance: \$${currentUser.accounts[i].balance} </h3>
        <table><tr>
        <th>  Transaction Type  </th>
        <th>  Amount  </th>
        <th>  Resulting Balance  </th>
        </tr>`;
        for(let j = (n-1); j>=(n-5);j--){
            if(j<0){
                continue;
            }
            tr += `<tr><td>${currentUser.accounts[i].transactions[j].type}</td> 
            <td>${currentUser.accounts[i].transactions[j].amount}</td> 
            <td>${currentUser.accounts[i].transactions[j].after_balance}</td></tr>`;
        }
        tr+=`</table> <br/>`;
    }
    return tr;
}

if(document.URL.includes("index.html")){
    document.getElementById("transaction").innerHTML = `<div>${generateLastFiveTransactions()}</div>`;
}

function deposit(account_number, amount) {

    let has_account = false;
    let account_index = 0;
    currentUser = JSON.parse(localStorage.getItem('CU'));
    for (let i = 0; i < currentUser.accounts.length; i++){
        if(currentUser.accounts[i].accountNumber == account_number){
            has_account = true;
            account_index = i;
            break;
        }
    }
    console.log("depositing ");
    if(!has_account){
        console.log("This account does not exist");
        return;
    }

    currentUser.accounts[account_index].balance += Number(amount);
    currentUser.accounts[account_index].transactions.push({
        type: "Deposit",
        amount: Number(amount),
        after_balance: currentUser.accounts[account_index].balance
    });
    localStorage.setItem('CU', JSON.stringify(currentUser));
}

function generateDepositForms() {
    currentUser = JSON.parse(localStorage.getItem('CU'));
    console.log(currentUser);
    let n = currentUser.accounts.length;
    let output = "";
    for (let i = 0; i < n; i++){
        output+= `<option value="${currentUser.accounts[i].accountNumber}">`;
    }

    return output;
}

if(document.URL.includes("deposit.html")){
    document.getElementById("acc_number").innerHTML = `${generateDepositForms()}`;
    document.addEventListener('DOMContentLoaded', ()=>{
        document.getElementById('deposit_submit_button').addEventListener('click', addDeposit);
    });
}

const addDeposit = (ev)=>{
    ev.preventDefault();
    console.log(document.getElementById("deposit_acc_number").value);
    deposit(document.getElementById("deposit_acc_number").value, document.getElementById("dep_amount").value);
    document.querySelector('form').reset();
    window.location.href = "/index.html";
}

const addWithdraw = (ev)=>{
    ev.preventDefault();
    console.log(document.getElementById("withdraw_acc_number").value);
    withdraw(document.getElementById("withdraw_acc_number").value, document.getElementById("wit_amount").value);
    document.querySelector('form').reset();
    window.location.href = "/index.html";
}

function withdraw(account_number, amount) {

    let has_account = false;
    let account_index = 0;
    currentUser = JSON.parse(localStorage.getItem('CU'));
    for (let i = 0; i < currentUser.accounts.length; i++){
        if(currentUser.accounts[i].accountNumber == account_number){
            has_account = true;
            account_index = i;
            break;
        }
    }
    console.log("withdrawing ");
    if(!has_account){
        console.log("This account does not exist");
        return;
    }

    if(currentUser.accounts[account_index].balance < Number(amount)){
        console.log("Insufficient Funds");
        return;
    }

    currentUser.accounts[account_index].balance -= Number(amount);
    currentUser.accounts[account_index].transactions.push({
        type: "Withdraw",
        amount: Number(amount),
        after_balance: currentUser.accounts[account_index].balance
    });
    localStorage.setItem('CU', JSON.stringify(currentUser));
}

if(document.URL.includes("withdraw.html")){
    document.getElementById("acc_number").innerHTML = `${generateDepositForms()}`;
    document.addEventListener('DOMContentLoaded', ()=>{
        document.getElementById('withdraw_submit_button').addEventListener('click', addWithdraw);
    });
}

if(document.URL.includes("account.html")){
    document.getElementById("account_info").innerHTML = `${generateAccountInfo()}`;
}

function generateAccountInfo(){

    currentUser = JSON.parse(localStorage.getItem('CU'));
    return `<h1>
    Email: ${currentUser.email}
    </h1><br>
    <h1>
    Phone: ${currentUser.phone}
    </h1>`;
}

if(document.URL.includes("login.html")){
    document.addEventListener('DOMContentLoaded', ()=>{
        document.getElementById('login_button').addEventListener('click', login);
    });
}

const login = (ev) =>{
    if(validate()){
        ev.preventDefault();
        window.location.href = "/index.html";

    }
}

function transfer(from_acc, to_acc, amount){

    if(from_acc == to_acc){
        return;
    }
    let has_account = false;
    let account_index = 0;
    currentUser = JSON.parse(localStorage.getItem('CU'));
    for (let i = 0; i < currentUser.accounts.length; i++){
        if(currentUser.accounts[i].accountNumber == from_acc){
            has_account = true;
            account_index = i;
            break;
        }
    }
    let user_index;
    let to_account_index;
    let has_to_account;
    for(let j = 0; j < users.length; j++){
        for (let i = 0; i < users[j].accounts.length; i++){
            if(users[j].accounts[i].accountNumber == to_acc){
                has_to_account = true;
                to_account_index = i;
                user_index = j
                break;
            }
        }
    }
    console.log("transfering ");
    if(!has_account || !has_to_account){
        console.log("This account does not exist");
        return;
    }

    if(currentUser.accounts[account_index].balance < Number(amount)){
        console.log("Insufficient Funds");
        return;
    }
    users[user_index].accounts[to_account_index].balance += Number(amount);
    if(users[user_index].username == currentUser.username){
        currentUser.accounts[to_account_index].balance += Number(amount);
    }
    currentUser.accounts[account_index].balance -= Number(amount);
    currentUser.accounts[account_index].transactions.push({
        type: "Transfer",
        amount: Number(amount),
        after_balance: currentUser.accounts[account_index].balance
    });
    localStorage.setItem('CU', JSON.stringify(currentUser));
}

if(document.URL.includes("transfer.html")){
    document.getElementById("acc_number").innerHTML = `${generateDepositForms()}`;
    document.getElementById("to_acc_number").innerHTML = `${generateDepositForms()}`;
    document.addEventListener('DOMContentLoaded', ()=>{
        document.getElementById('transfer_submit_button').addEventListener('click', addTransfer);
    });
}

const addTransfer = (ev) => {
    ev.preventDefault();
    transfer(document.getElementById("transfer_acc_number").value, document.getElementById("transfer_to_acc_number").value, document.getElementById("tr_amount").value);
    document.querySelector('form').reset();
    window.location.href = "/index.html";
}