import React, {Component} from 'react'
import VirtualizedSelect from 'react-virtualized-select'
import CONST from '../Const'

class CreateTicketForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoaded: false,
            priority: [],
            selectedPriority: "",
        }
    }

    getPriorityList(){
        var url = new URL(CONST.URL.BACKEND_BASE_URL + '/api/ticket/priority')
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
                    priority: res.data
                });
            }))
            .catch(error => {
                throw (error.message)
            });
    }


    selectDataProvider() {
        const {priority}  = this.state
        var  priorityList = Object.keys(priority).map(function(key) {
            return [Number(key), priority[key]];
        });
        let result = [];
        if (priorityList.length > 0) {
            priorityList.forEach(item  => {
                result.push({
                    value: item[0], label: item[1]
                })
            })
            return result;
        }
        return result;
    }

    componentDidMount() {
        this.getPriorityList();
    }
    render() {
        const priorityList = this.selectDataProvider();

        let priorities = <VirtualizedSelect
            name="form-field-name"
            value={this.state.selectedPriority}
            options={priorityList}
            onChange={(priority) => this.setState({selectedPriority : priority})}
            className="user-select-cmp"
        />
        return (
            <div>
                <div>
                    <label htmlFor="title">Title: </label>
                    <input className="title" id="title"></input>
                </div>
                <div>
                    <label htmlFor="description">Description: </label>
                    <input className="description" id="description"></input>
                </div>
                <div>
                    <label htmlFor="email">Email: </label>
                    <input className="email" id="email"></input>
                </div>
                <div>
                    <label htmlFor="priority">Priority: </label>
                    {this.state.isLoaded ?
                        priorities
                        : null
                    }
                </div>
                <div>
                    <button>Create</button>
                </div>
            </div>
        )
    }
}

export default CreateTicketForm