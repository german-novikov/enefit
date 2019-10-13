import React, {Component} from 'react';
import globalVariables from "../GlobalVariables"

class OpenCreateFormButton extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showCreateForm: globalVariables.show.createForm
        }
    }

    showCreateForm(){
        this.setState({
            showCreateForm:true
        })
        globalVariables.show.createForm = true
    }

    sendData = () => {
        this.props.parentCallback(true);
    }


    render() {
        return (
            <div>
                <button onClick={this.props.handler}>Create ticket</button>
            </div>
        )
    }
}

export default OpenCreateFormButton
