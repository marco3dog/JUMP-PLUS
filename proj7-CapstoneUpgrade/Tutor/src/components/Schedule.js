import React, { useState, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link, useNavigate} from "react-router-dom";
import CourseApi from "../APIs/CourseApi";
import SubjectApi from "../APIs/SubjectApi";
import { getSignedInUser } from "../Util/auth";
import './CSS/schedule.css';
const Schedule = () => {

  const navigate = useNavigate();

  const [SubjectID, setSubjectID] = useState(1);
  const [subjectList, setSubjectList] = useState([]);
  const [Availability, setAvailability] = useState([]);
  const [Hourly, setHourly] = useState(0);
  const [UserID, setUserID]=useState(0)

  const changeAvail = (bool, day) =>{
    bool ? Availability.push(day) : Availability.filter(e => e !== day)
    setAvailability(Availability)
  }
  const handleSubmit = (event) => {
    console.log(Hourly + "Before If")
    if(Hourly < 0){
      console.log(Hourly + "Inside If")
      alert("Hourly rate must be greater than 0")
      return
    }
    let checks = document.getElementsByName("day")
    for(let i =0; i < checks.length; i++){
      if(checks[i].checked){
        Availability.push(checks[i].value)
      }
    }
    let av = Availability.join(", ");
    console.log(av)
    console.log(Availability)
    const course = {
      "subject": {"id": SubjectID},
      "hourly": Hourly,
      "availability": av,
      "tutor":{"id":UserID}
    }
    console.log(course)
CourseApi.addCourse(course)
event.preventDefault()

  };
  useEffect(() => {
    SubjectApi.getSubjects(setSubjectList);
    setUserID(getSignedInUser().id)
  }, []);
 
  return (
    <section className="schedule-body">
      <div className="schedule-title">
            <h2 className="text-center mb-5">Schedule a Session</h2>
      </div>
        <div className="wrapper">
            <div className="first inner-wrapper">
              <FontAwesomeIcon icon="fa-solid fa-magnifying-glass" />
              <Link className="btn book-session" to="/courses">Book a Session</Link>
              <p>Select a course you want to recieve tutoring in</p>
            </div>
            <div className="second inner-wrapper">
              <FontAwesomeIcon icon="fa-solid fa-person-chalkboard" />
              <button type="button" className="btn add-session" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Add a Session</button>
              <p>Add a course you want to tutor, add a rate and allow students to schedule you</p>
            </div>
          <div className="modal fade" id="staticBackdrop"
            data-bs-backdrop="static" data-bs-keyboard="false"
            tabIndex="-1"
            aria-labelledby="staticBackdropLabel" aria-hidden="true"
          >
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title" id="staticBackdropLabel">
                    Offer a Course
                  </h5>
                  <button
                    type="button"
                    className="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                  ></button>
                </div>
                <div className="modal-body">
                  <form className="form" onSubmit={handleSubmit}>
                    <div className="row mb-3">
                      <label className="form-label">Subject</label>
                      <select
                        className="form-control"
                        value={SubjectID}
                        onChange={(event) => {
                          setSubjectID(event.target.value);
                        }}
                      >
                        <option disabled selected>
                          Select an Option
                        </option>
                        {subjectList.map((s) => (
                          <option key={s.id} value={s.id}>{s.name} </option>
                        ))}
                      </select>
                    </div>
                    <div className="row mb-3">
                      <label className="form-label">Hourly Rate</label>
                      <input
                        type="number"
                        className="form-control"
                        required
                       // min="0"
                        step="0.01"
                        value={Hourly}
                        onChange={(event) => {
                          setHourly(event.target.value);
                        }}
                      />
                    </div>

                    <div className="row mb-3">
                      <label className="form-label">Availability</label>
                      {/* <input
                        type="text"
                        className="form-control"
                        value={Availability}
                        onChange={(event) => {
                          setAvailability(event.target.value);
                        }}
                      ></input> */}
                      <div class="form-check">
                      <input class="form-check-input" name="day" type="checkbox" value="Mon" id="Monday"/>
                      <label class="form-check-label" for="flexCheckDefault">
                        Monday
                      </label>
                      </div>
                      <div class="form-check">
                      <input class="form-check-input" name="day" type="checkbox" value="Tues" id="Tuesday"/>
                      <label class="form-check-label" for="flexCheckDefault">
                        Tuesday
                      </label>
                      </div>
                      <div class="form-check">
                      <input class="form-check-input" name="day" type="checkbox" value="Wed" id="Wednesday"/>
                      <label class="form-check-label" for="flexCheckDefault">
                        Wednesday
                      </label>
                      </div>
                      <div class="form-check">
                      <input class="form-check-input" name="day" type="checkbox" value="Thurs" id="Thursday"/>
                      <label class="form-check-label" for="flexCheckDefault">
                        Thursday
                      </label>
                      </div>
                      <div class="form-check">
                      <input class="form-check-input" name="day" type="checkbox" value="Fri" id="Friday"/>
                      <label class="form-check-label" for="flexCheckDefault">
                        Friday
                      </label>
                      </div>
                      <div class="form-check">
                      <input class="form-check-input" name="day" type="checkbox" value="Sat" id="Saturday"/>
                      <label class="form-check-label" for="flexCheckDefault">
                        Saturday
                      </label>
                      </div>
                      <div class="form-check">
                      <input class="form-check-input" name="day" type="checkbox" value="Sun" id="Sunday"/>
                      <label class="form-check-label" for="flexCheckDefault">
                        Sunday
                      </label>
                      </div>
                    </div>
                    <div className="modal-footer">
                      <input id="submitButton" type="submit" className="btn submit-button" value="Submit"  data-bs-dismiss="modal"></input>
                      <button id="submitButton" type="button" className="btn close-button" data-bs-dismiss="modal">Close</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div> 
    </section>
  );
};
export default Schedule;
