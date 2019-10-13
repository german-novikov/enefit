import React, {Component} from 'react'

import TableComponent from '../components/TableComponent'
import OpenCreateFormButton from "../components/OpenCreateFormButton";
import CreateTicketForm from "./CreateTicketForm";
import globalVariables from "../GlobalVariables"


class Desk extends Component {
    constructor(props) {
        super(props);
        this.handler = this.handler.bind(this)

        this.state = {
            showCreateForm : globalVariables.show.createForm
        };
    }

    h
    handler(showForm) {
        this.setState({
            showCreateForm: showForm
        })
    }


    display(){
        return (
            <div className="main">
                <div className="header">Tickets Desk</div>
                <div className="desk">
                    <TableComponent/>
                </div>
                <div>
                    <OpenCreateFormButton handler = {this.handler}/>
                </div>
                <div>
                    {
                        this.state.showCreateForm ? <CreateTicketForm/> : null
                    }
                </div>
            </div>
        )
    }

    render() {
        return (
            <div className="main">
                {/*<div className="header">Tickets Desk</div>*/}
                {/*<div className="desk">*/}
                    {/*<TableComponent/>*/}
                {/*</div>*/}
                {/*<div>*/}
                    {/*<OpenFormButton/>*/}
                {/*</div>*/}
                {/*<div>*/}
                    {/*{*/}
                        {/*this.state.showCreateForm ? <CreateTicketForm/> : null*/}
                    {/*}*/}
                {/*</div>*/}
                {this.display()}
            </div>
        )
    }
}

export default Desk