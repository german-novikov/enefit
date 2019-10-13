import React, {Component} from 'react';
import CONST from '../Const'
import Moment from 'react-moment'

class TableComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            tickets: {},
            sortOrder:{
                status: "ASC",
                priority: "ASC",
                createdDate: "ASC"
            }
        };
    }

    revertOrderValue(order) {
        if("DESC" === order){
            return "ASC"
        }
        return "DESC"
    }

    getAllTickets() {
        var url = new URL(CONST.URL.BACKEND_BASE_URL + '/api/desk')
        return fetch(url,
            {
                method: 'get',
                credentials: "same-origin",
                headers: {'Content-Type': 'application/json', 'Accept': 'application/json, text/plain, */*'},
            }).then(response =>
            response.json().then(data => ({
                    data: data,
                    status: response.status
                })
            ).then(res => {
                this.setState({
                    error : null,
                    isLoaded: true,
                    tickets: res.data
                });
            }))
            .catch(error => {
                throw (error.message)
            });
    }

    sortTicketsByStatus(order) {
        var url = new URL(CONST.URL.BACKEND_BASE_URL + '/api/desk/sort_by_status')
        url.searchParams.append("orderBy", order)
        return fetch(url,
            {
                method: 'get',
                credentials: "same-origin",
                headers: {'Content-Type': 'application/json', 'Accept': 'application/json, text/plain, */*'},

            }).then(response =>
            response.json().then(data => ({
                    data: data,
                    status: response.status
                })
            ).then(res => {
                this.setState({
                    error : null,
                    isLoaded: true,
                    tickets: res.data,
                    sortOrder:{
                        status: this.revertOrderValue(order)
                    }
                });
            }))
            .catch(error => {
                throw (error.message)
            });
    }

    sortTicketsByPriority = (order) => {
        var url = new URL(CONST.URL.BACKEND_BASE_URL + '/api/desk/sort_by_priority')
        url.searchParams.append("orderBy", order)
        return fetch(url,
            {
                method: 'get',
                credentials: "same-origin",
                headers: {'Content-Type': 'application/json', 'Accept': 'application/json, text/plain, */*'},
            }).then(response =>
            response.json().then(data => ({
                    data: data,
                    status: response.status
                })
            ).then(res => {
                this.setState({
                    error : null,
                    isLoaded: true,
                    tickets: res.data,
                    sortOrder:{
                        priority: this.revertOrderValue(order)
                    }
                });
            }))
            .catch(error => {
                throw (error.message)
            });
    }

    sortTicketsByCreatedDate = (order) => {
        var url = new URL(CONST.URL.BACKEND_BASE_URL + '/api/desk/sort_by_created_date')
        url.searchParams.append("orderBy", order)
        return fetch(url,
            {
                method: 'get',
                credentials: "same-origin",
                headers: {'Content-Type': 'application/json', 'Accept': 'application/json, text/plain, */*'},
            }).then(response =>
            response.json().then(data => ({
                    data: data,
                    status: response.status
                })
            ).then(res => {
                this.setState({
                    error : null,
                    isLoaded: true,
                    tickets: res.data,
                    sortOrder:{
                        createdDate: this.revertOrderValue(order)
                    }
                });
            }))
            .catch(error => {
                throw (error.message)
            });
    }


    getRowsData = function(){
        const {tickets} = this.state;
        return tickets.map((ticket, index)=>{
            return (
                <tr key={index}>
                    <td>{index}</td>
                    <td>{ticket.title}</td>
                    <td>{ticket.status}</td>
                    <td>{ticket.priority}</td>
                    <td>{Moment(ticket.createdDate).format('YYYY-MM-DD')}</td>
                </tr>
            )
        })
    }

    componentDidMount() {
        this.getAllTickets();
    }


    render() {
        const {error, isLoaded, sortOrder} = this.state;
        if (error) {
            return <div>Error: {error.message}</div>;
        } else if (!isLoaded) {
            return <div>Loading...</div>;
        } else {
            return (
                <div>
                    <table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Title</th>
                            <th><a onClick={() => this.sortTicketsByStatus(sortOrder.status)}>Status</a></th>
                            <th><a onClick={() => this.sortTicketsByPriority(sortOrder.priority)}>Priority</a></th>
                            <th><a onClick={() => this.sortTicketsByCreatedDate(sortOrder.createdDate)}>Created date</a></th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.getRowsData()}
                        </tbody>
                    </table>
                </div>

            );
        }
    }
}

export default TableComponent